import React, { useEffect, useState } from 'react';
import './App.css';
import Feed from './components/Feed';
import Header from './components/Header';
import PostForm from './components/PostForm';
import UserPage from './components/UserPage';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import LogInForm from './components/LoginForm';
// import Post from './components/Post';
import RegistrationForm from './components/RegistrationForm';
import axios from 'axios';

function App() {
  const [allUsers, setAllUsers] = useState([]);

  //this line was for my testing purposes
  console.log(allUsers);

  const [newUser, setNewUser] = useState(true);
  const [user, setUser] = useState({});
  const [otherUser, setOtherUser] = useState({});

  //using useEffect to grab all users and put them in an array
  //upon application loading for search purposes
  useEffect(()=>{
    axios
    .get('http://localhost:9001/Project2/api/user/getAllUsers')
    .then((response)=>{
      setAllUsers(response.data);
    })
    .catch(console.error);
  },[user]);

  return (
    <Router>
      <Header setOtherUser={setOtherUser} allUsers={allUsers}/>
      <Route exact path="/" render={()=> newUser === true ? <RegistrationForm setNewUser={setNewUser} /> : <LogInForm setAllUsers={setAllUsers} setUser={setUser} setNewUser={setNewUser} />}/>
      <Route path="/user" render={()=><UserPage user={user} setUser={setUser}/>}/>


    </Router>
  );
}

export default App;
