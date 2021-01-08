import React from 'react';

function Header() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light" id="nav">
            <div className="container-fluid" id="nav-flex">
                <span className="navbar-brand mb-0 h1">Project2 - Air Nomads</span>

                <form className="d-flex" id="navbar-form">
                    <input className="search-bar" type="search" placeholder="Search" aria-label="Search" id="search-bar" />
                    <button className="btn" type="submit">Search</button>
                </form>
            </div>
        </nav>
    );
}
export default Header;
