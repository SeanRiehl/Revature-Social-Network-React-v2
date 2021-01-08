import React, { useState } from 'react';

function LogInForm({ setNewUser }) {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const handleSubmitClick = (e) => {
        e.preventDefault();
        console.log(email)
        console.log(password)

        // MAKE SURE THE ACCOUNT EXISTS, IF SO LOAD THE POST FEED PAGE

        // if (password === confirmPassword) {
        //     // sendDetailsToServer();
        //     console.log("Passwords do match");
        // } else {
        //     // props.showError('Passwords do not match');
        //     console.log("Passwords do not match");
        // }
    }

    return (
        <div className="container">
            <div className="row justify-content-center align-items-center" id="login-form">
                <div className="col-md-4 col-sm-4 col-xs-12" id="content">
                    <form id="form">
                        <h4>Log In</h4>
                        <div className="form-group">
                            <label className="text-start" htmlFor="exampleInputEmail1">Email Address:</label>
                            <input type="email" className="form-control" id="email" aria-describedby="emailHelp"
                                placeholder="Email Address" value={email} onChange={(e) => {
                                    setEmail(e.target.value)
                                }} />
                        </div>

                        <div className="form-group">
                            <label htmlFor="exampleInputPassword1">Password:</label>
                            <input type="password" className="form-control" id="password"
                                placeholder="Password" value={password} onChange={(e) => {
                                    setPassword(e.target.value)
                                }} />
                        </div>

                        <button type="submit" className="btn" onClick={(e) => {
                            handleSubmitClick(e)
                        }}>Log In</button>

                        <button className="btn" onClick={(e) => {
                            e.preventDefault()
                            setNewUser(true)
                        }}>Register an Account</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default LogInForm;
