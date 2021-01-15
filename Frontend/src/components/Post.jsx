import React, { useState } from 'react';

function Post(props) {
    const [like, setLike] = useState(false);
    const [count, setCount] = useState(9);

    return (
        // <div className="card">
        //     <div className="card-body">
        //         <img src={props.data.postProfileImage} className="img-thumbnail" alt="..." />
        //         <p className="card-text">{props.data.postAuthor}</p>
        //         <p className="card-text">{props.data.postTimestamp}</p>
        //         <p className="card-text">{props.data.postBody}</p>
        //     </div>
        //     <img src={props.data.postImage} className="card-img-top" alt="..." />
        // </div>

        // SWITCH OUT THE IMAGE SOURCES/POST AUTHOR/POST TIMESTAMP/POST TEXT WITH {props.data.post_____} LIKE ABOVE

        <div className="col-4" id="post-div">
            <div className="card" id="post">
                <div className="card-body">
                    <div className="postHeader">
                        <div id="postHeaderInfo">
                            <img src="https://images.unsplash.com/photo-1609512236252-f2abe56cfd2f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" className="img-thumbnail" alt="..." id="profilePicture" />
                            <div id="postInfo">
                                <p id="postAuthor">AUTHOR</p>
                                <p id="postTimestamp">1:25am</p>
                            </div>
                        </div>
                        <div id="postLikeButton">
                            <span id="count">{count}</span>
                            <svg xmlns="http://www.w3.org/2000/svg" width="36" height="36" fill="currentColor" className={`${like ? `blue` : ``} bi bi-arrow-up-square-fill`} id="svg" viewBox="0 0 16 16" onClick={
                                function () {
                                    setLike(!like)
                                    if (like) {
                                        setCount(count - 1)
                                    } else {
                                        setCount(count + 1)
                                    }
                                }
                            }>
                                <path d="M2 16a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2zm6.5-4.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 1 0z" />
                            </svg>
                        </div>
                    </div>
                    <p className="card-text" id="postText">Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi tempora accusantium velit blanditiis omnis natus numquam harum, recusandae perspiciatis, doloribus itaque voluptatum deserunt laborum eos. Optio nam quas repellendus ea ratione voluptates natus qui error voluptatem dolores!</p>
                </div>
                <div id="postPicture">
                    <img src="https://images.unsplash.com/photo-1609512236252-f2abe56cfd2f?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=700&q=80" alt="..." />
                </div>
            </div>
        </div>
    );
}

export default Post
