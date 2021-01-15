import axios from 'axios';
import React, { useState } from 'react';

function PostForm(props) {
    /*
     * USE THIS HOOK TO SET THE IMAGE
     * If no image is given, make it <img src="" alt="">
     * Otherwise make it <img src="IMAGE_URL_HERE" alt="">
     *
     * ADD AN ON-CLICK EVENT LISTENER TO THE IMAGE
     * When the user clicks the upload file image, ask them for an image url
     * And set the img const below to that image url
     *
     * ALSO ADD AN ON-CLICK EVENT LISTNER TO THE USERNAME
     * When the user clicks on a username, take them to that user's page
     * Do the same thing in the posts component so any username works
     * [See the posts like button for an example on event listeners]
     *
     * ALSO ADD AN ON-HOVER EVENT LISTENER TO THE USERNAME
     * When the user mouses over a username, have it become underlined
     *
     * ALSO ADD AN ON-CLICK EVENT LISTENER TO THE LEFT HEADER TEXT
     * When a user clicks on the "Project2 - Air Nomads" text,
     * take them back to the page that displays the post creation/feed
     *
     * FOR THE SEARCH BAR
     * If a user searches for a user and they exist, display their user page
     * but if the user does not exist, stay on the same page
     *
     * TO ALLOW THE USER UPLOAD MULTIPLE IMAGES
     * Create a new image tag for every image and insert it
     */
    const [imageExists, setPicture] = useState(false);
    const [isSent, setIsSent] = useState(false);
    const image = 'https://images-ext-2.discordapp.net/external/7q7IKmvlGqtofDERdSuD__vUPewQ6mQg2T2cDuDu0bc/https/project2-profile-pic.s3.us-east-2.amazonaws.com/try2.png';
    return (
        <div className="container" id="container">
            <form id="postCreation" onSubmit={e => handlePostSubmitClick(e, props.user, setIsSent,setPicture)}>
                <div id="postCreationFlex">
                    <div id="postCreationImage">
                        <img src="https://images.unsplash.com/photo-1609512236252-f2abe56cfd2f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="..." id="postCreationPicture" />
                    </div>
                    <textarea minLength="5" type="text" id="postCreationText" name="postText" placeholder={props.user ? `What's on your mind, ${props.user.firstName}?` : null} />
                    <label className="position-relative upload-post-picture">
                        <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" className="bi bi-file-earmark-arrow-up" viewBox="0 0 16 16">
                            <path d="M8.5 11.5a.5.5 0 0 1-1 0V7.707L6.354 8.854a.5.5 0 1 1-.708-.708l2-2a.5.5 0 0 1 .708 0l2 2a.5.5 0 0 1-.708.708L8.5 7.707V11.5z" />
                            <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z" />
                        </svg>
                        <input type="file" name="file" className="position-absolute" />
                    </label>
                </div>
                {/* Conditionally render in the uploaded image */}
                {imageExists ? <div id="postCreationAddedImage">
                    <img src={image} alt="" />
                </div> : null}
                <input disabled={isSent} type="submit" value="Submit" id="submit" className="btn" />
            </form>
        </div>
    );
}

function handlePostSubmitClick(e,user,setIsSent, setPicture) {
    e.preventDefault();
    setIsSent(true);
    let {postText, file} = e.currentTarget.elements
    console.log(user);
    if(user) {
        const formData = new FormData(e.currentTarget);
        const init = {
            method: "POST",
            url: `http://localhost:9001/Project2/api/picture?from=post&userId=${user.userId}`,
            headers: {
                "content-type": "multipart/form-data"
            },
            data: formData
        }
        axios(init)
        .then(resp => {
            file.value = "";
            postText.value = "";
            setIsSent(false);
        })
        .catch(console.error)
        setIsSent(false);

    }
}
export default PostForm
