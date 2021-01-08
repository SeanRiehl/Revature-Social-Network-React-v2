import React from 'react'
import Post from './Post'

function Feed() {
    return (
        <div className="container">
            <div className="row justify-content-start">
                <Post />
                <Post />
                <Post />
                <Post />
                <Post />
                <Post />
            </div>
        </div>
    );
}

export default Feed
