import React, { useEffect, useState } from 'react';
import SearchedUser from './SearchedUser';
import {useHistory} from 'react-router-dom';
function Header(props) {
    const [searchUser, setSearchUser] = useState("");
    const [searchedUserList, setSearchedUserList] = useState([]);
    let history = useHistory();

    function handleSearchSubmit(e){
        e.preventDefault();
        for(let i = 0; i < props.allUsers.length; i++) {
            if(props.allUsers[i].username === searchUser){
                //props.setOtherUser(props.allUser[i])
                history.push("/otherUser")
            }
        }
    }

    useEffect(()=>{
        const storeSearchedUser = [];
        console.log(searchedUserList)
        if(!searchUser) return;
        for(let i = 0; i < props.allUsers.length; i++) {
            if(props.allUsers[i].username.includes(searchUser)){
                storeSearchedUser.push(props.allUsers[i]);
            }
        }
        setSearchedUserList(storeSearchedUser);
    },[searchUser])

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light" id="nav">
            <div className="container-fluid" id="nav-flex">
                <span className="navbar-brand mb-0 h1">Project2 - Air Nomads</span>

                <form className="d-flex position-relative" id="navbar-form" onSubmit={handleSearchSubmit}>
                    {searchUser? returnUser(searchedUserList,setSearchUser) : null}
                    <input onChange= {e =>{handleSearchOnChange(e,setSearchUser)}} className="search-bar" type="search" placeholder="Search" aria-label="Search" id="search-bar" value={searchUser} autoComplete="off"/>
                    <button className="btn" type="submit">Search</button>
                    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" className="bi bi-arrow-right-square-fill" id="nav-logout" viewBox="0 0 16 16">
                        <path d="M0 14a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v12zm4.5-6.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5a.5.5 0 0 1 0-1z" />
                    </svg>
                </form>
            </div>
        </nav>
    );
}
export default Header;

function handleSearchOnChange(e,setSearchUser) {
    // const input = e.currentValue;
    setSearchUser(e.target.value);
}

function returnUser(allSearchedUser, setSearchUser) {
    console.log(allSearchedUser)
    if(!allSearchedUser.length) {allSearchedUser = [null]};
    return (
        <div className="position-absolute search-user w-50 border border-dark rounded">
            {allSearchedUser.map(searchedUser => {
                return <SearchedUser key={searchedUser ? searchedUser.userId : -1} setSearchUser={setSearchUser} searchedUser={searchedUser}/>
            })}
        </div>
    )
}
