package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.hasher.PBDKF2Hasher;
import com.project.model.User;
import com.project.service.UserService;


@Controller
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	//FIELDS
	private UserService usi;
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public UserController(UserService useServ) {
		super();
		this.usi = useServ;
	}

	/**
	 * This method returns the user and creates a session
	 * @param session - this is the HttpSession object that Spring will inject for us
	 * @param newUser - this is the user JSON that will be added to the DB and subsequently added to the session
	 */
	@PostMapping("/newUser")
	public @ResponseBody ResponseEntity<String> createUser(HttpSession session, @RequestBody User newUser) {
		
		/*
		 * IN ORDER FOR THIS METHOD TO FUNCTION APPROPRIATELY WE NEED TO MAKE SURE ALL REQUISITE FORMS HAVE BEEN
		 * VALIDATED ON THE FRONT END.
		 * 
		 * I can add checks here if need be, but I think it'll be more efficient to have those checks be done
		 * BEFORE the newUser object is sent through as a JSON
		 * 
		 *  - Joe
		 */
		User u = usi.getUserByUsername(newUser.getUsername());
		
		//checking if user by that name already exists
		if(u != null) {
			return new ResponseEntity<String>("false", HttpStatus.CONFLICT);
		}
		
		//this is where we hash their requested password
		//we also need to store the salt that we create for this process
		
		//creating the hasher instance and getting a new random array of bytes which we call "salt"
		PBDKF2Hasher hasher = new PBDKF2Hasher();
		String salt = hasher.newSalt();
		
		String password = newUser.getPassword();
		
		String hashedPassword = hasher.hash(password, salt);
		
		//setting the relevant user attributes BEFORE passing it to the DB
		newUser.setSalt(salt);
		newUser.setPassword(hashedPassword);
		
		//actually passing the user with the new hashed password to the DB
		boolean gotUser = usi.addUser(newUser);
		
		if(gotUser == false || newUser == null) return null;
		
		return new ResponseEntity<String>("true", HttpStatus.CREATED);
		
	}
	
	/**
	 * This method is sent by a GET request and gets the username from the parameters and 
	 * will find that user in the database.
	 * It will return a JSON of the user object except for the password.
	 * @param session - this is the HttpSession object that Spring will inject for us
	 * @param userToGet - this is the user JSON that will be gotten from the DB, added to the session and returned if found
	 */
	@GetMapping("/getUser")
	public @ResponseBody User getUser(HttpSession session){
		
		User user = (User) session.getAttribute("loggedInUser");
		
		System.out.println("In /getuser endpoint " + user);
		
		if(user == null) {
			return null;
		}
		
		return user;
		
	}
	
	@GetMapping("/getAllUsers")
	public @ResponseBody List<User> getAllUsers(){
		System.out.println("In /getAllUsers endpoint");
		System.out.println(usi.getAllUsers());
		return usi.getAllUsers();
	}
	
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<User> login(HttpSession session, @RequestBody User userToLogin){
		
		
		//making a call to the DB to see if the account with that username exists
		User u = usi.getUserByEmail(userToLogin.getEmail());
		System.out.println(u);
		if(u==null) {
			//maybe use another HttpStatus to send back here...so as to differentiate this error
			//from the login credentials being invalid if the account DOES exist.
			return new ResponseEntity<User>(u, HttpStatus.EXPECTATION_FAILED);
		}
		
		
		String username = u.getUsername();
		String password = userToLogin.getPassword();
		String salt = u.getSalt();
		
		//new hasher instance and hashing the password to compare with the one gotten from the DB
		PBDKF2Hasher hasher = new PBDKF2Hasher();
		
		String hashedPassword = hasher.hash(password, salt);
		
		
		
		
		boolean bool = usi.verifyCredentials(username, hashedPassword);
		
		if(bool) {
			session.setAttribute("loggedInUser", u);
			System.out.println("In /login endpoint: " + session.getAttribute("loggedInUser"));
			return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
		}else {
			u = null;
			return new ResponseEntity<User>(u, HttpStatus.BAD_REQUEST);
		}
		
	}
	
//	@UpdateMapping("/updateUser")
//	public @ResponseBody 

}
