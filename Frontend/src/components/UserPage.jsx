import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Feed from './Feed';
import PostForm from './PostForm';

function UserPage(props) {
    const user = props.user;
    const [post, setPost] = useState(null);
    useEffect(() => getData(setPost, user),[]);
    return (
        <div className="container">
            <div className="container row mt-5">
                    <>
                    <img src={window.location.origin + "/profile-picture.png"} className="p-0 img-fluid col-3 border border-dark" alt="Profile" />
                    <div className="mx-3 col d-flex flex-column justify-content-evenly">
                        <div>Username: {user.username }</div>
                        <div>Name: {user.firstName + " " + user.lastName}</div>
                        <div>Email: {user.email}</div>
                    </div>
                </>
            </div>
            <PostForm user={user}/>
            <Feed />
        </div>
    );
}

const getData = async (setPost, user) => {

    // await axios.post("http:localhost:9001/Project2/api/post", user)
    //     .then((response) => setPost(response))

}

export default UserPage;
