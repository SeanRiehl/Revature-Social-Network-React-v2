package com.project.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.servletcontroller.LoginController;
import com.project.servletcontroller.UserController;

public class RequestHelper {

	public static void process(HttpServletRequest req, HttpServletResponse res) {
		String uri = req.getRequestURI();
		String method = req.getMethod();
		System.out.println(uri + " " + method);
		switch(uri) {
		case "/Project2/api/login":
			LoginController lc = new LoginController();
			try {
				lc.login(req,res);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "/Project2/api/user" :
			UserController uc = new UserController();
			if(method.equals("GET")) {
				System.out.println("get method");
				try {
					uc.getUser(req, res);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			if(method.equals("POST")) {
				System.out.println("post method");
				try {
					uc.createUser(req, res);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(method.equals("PUT")) {
				System.out.println("put method");
				
			}
			break;
		case "/Project2/api/post":
			if(method.equals("GET")) {
				
			}
			if(method.equals("POST")) {
				
			}
			break;
		default: break;
		}
	}
}
