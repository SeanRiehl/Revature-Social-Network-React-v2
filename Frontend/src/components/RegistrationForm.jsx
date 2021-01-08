import React, { useState } from 'react';

function RegistrationForm({ setNewUser }) {
    const [email, setEmail] = useState("")
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [confirmPassword, setConfirmedPassword] = useState("")

    const handleSubmitClick = (e) => {
        e.preventDefault();
        console.log(email)
        console.log(username)
        console.log(password)
        console.log(confirmPassword)

        // CONFIRM THE EMAIL DOES NOT ALREADY EXIST IN THE TABLE

        if (password === confirmPassword) {
            // sendDetailsToServer();
            console.log("Passwords match");
        } else {
            // props.showError('Passwords do not match');
            alert("Passwords do not match");
        }
    }

    // const sendDetailsToServer = () => {
    //     if (state.email.length && state.password.length) {
    //         props.showError(null);
    //         const payload = {
    //             "email": state.email,
    //             "password": state.password,
    //         }

    //         axios.post(API_BASE_URL + '/user/register', payload)
    //             .then(function (response) {
    //                 if (response.status === 200) {
    //                     setState(prevState => ({
    //                         ...prevState,
    //                         'successMessage': 'Registration successful. Redirecting to home page..'
    //                     }))
    //                     redirectToHome();
    //                     props.showError(null)
    //                 } else {
    //                     props.showError("Some error ocurred");
    //                 }
    //             })
    //             .catch(function (error) {
    //                 console.log(error);
    //             });
    //     } else {
    //         props.showError('Please enter valid username and password')
    //     }
    // }

    return (
        <div className="container">
            <div className="row justify-content-center align-items-center" id="login-form">
                <div className="col-md-4 col-sm-4 col-xs-12 content-reg" id="content">
                    <form id="form">
                        <h4>Register a New Account</h4>
                        <div className="form-group">
                            <label className="text-start" htmlFor="exampleInputEmail1">Email Address:</label>
                            <input type="email" className="form-control" id="email" aria-describedby="emailHelp"
                                placeholder="Email Address" value={email} onChange={(e) => {
                                    setEmail(e.target.value)
                                }} />
                        </div>

                        <div className="form-group">
                            <label htmlFor="exampleInputUsername1">Username:</label>
                            <input type="username" className="form-control" id="username"
                                placeholder="Username" value={username} onChange={(e) => {
                                    setUsername(e.target.value)
                                }} />
                        </div>

                        <div className="form-group">
                            <label htmlFor="exampleInputPassword1">Password:</label>
                            <input type="password" className="form-control" id="password"
                                placeholder="Password" value={password} onChange={(e) => {
                                    setPassword(e.target.value)
                                }} />
                        </div>

                        <div className="form-group">
                            <label htmlFor="exampleInputPassword1">Confirm Password:</label>
                            <input type="password" className="form-control" id="confirmPassword" placeholder="Confirm Password" onChange={(e) => {
                                setConfirmedPassword(e.target.value);
                            }} />
                        </div>

                        <button type="submit" className="btn" onClick={(e) => {
                            handleSubmitClick(e)
                        }}>Register an Account</button>

                        <button className="btn" onClick={(e) => {
                            e.preventDefault()
                            setNewUser(false)
                        }}>Already a User?</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default RegistrationForm;
