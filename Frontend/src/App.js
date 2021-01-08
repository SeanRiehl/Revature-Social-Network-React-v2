import React, { useState } from 'react';
import './App.css';
import Feed from './components/Feed';
import Header from './components/Header';
// import LogInForm from './components/LoginForm';
// import Post from './components/Post';
// import RegistrationForm from './components/RegistrationForm';

function App() {
  const [newUser, setNewUser] = useState(true)

  return (
    <div>
      <Header />
      {/* {newUser === true ? <RegistrationForm setNewUser={setNewUser} /> : <LogInForm setNewUser={setNewUser} />} */}
      <Feed />
    </div>
  );
}

export default App;
