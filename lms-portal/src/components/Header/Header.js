import './Header.scss';
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useCookies } from 'react-cookie';
import { logout } from "services/AuthService";
import { useDispatch } from "react-redux";
import Constants from "Constants";

const Header = () => {
    const nav = useNavigate();
    const dispatch = useDispatch();
    const [cookies] = useCookies([Constants.COOKIE.LMS_TOKEN, Constants.COOKIE.LMS_ROLE, Constants.COOKIE.LMS_USER]);

    const isAuth = cookies[Constants.COOKIE.LMS_TOKEN];
    const role = cookies[Constants.COOKIE.LMS_ROLE];
    const username = cookies[Constants.COOKIE.LMS_USER];

    const onLogout = () => {
        dispatch(logout({username, role}))
            .then(async (response) => {
                nav('/');
            });
    }

    return (
        <header className="header-area">
            <div className="container">
                <div className="header-top-area">
                    <div className="row">
                        <div className="col-md-8">
                            <div className="header-top-left">
                                <p><i className="fa fa-phone-square"></i> 1 (800) 555 5555</p>
                                <p><i className="fa fa-envelope"></i> contact@lms.com</p>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="header-top-right">
                                {!isAuth && (
                                    <p>
                                        <Link to="/login"><i className="fa fa-sign-in"></i>Go to Login</Link>
                                    </p>
                                )}
                                {!!isAuth && (
                                    <p>
                                        Welcome, <strong>{username}</strong> &nbsp;
                                        <Link to="#" onClick={onLogout}><i className="fa fa-sign-out"></i>Logout</Link>
                                    </p>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="header-main-area">
                    <div className="row">
                        <div className="col-lg-3 col-sm-12">
                            <div className="site-logo">
                                <Link to="/">
                                    <img src="/logo.png" alt="LMS" />
                                </Link>
                            </div>
                        </div>
                        <div className="col-lg-7">
                            <div className="mainmenu">
                                <nav>
                                    <ul id="navigation">
                                        <li className="active">
                                            <Link to="/">Home</Link>
                                        </li>
                                        {role === "Admin" && (
                                            <>
                                                <li>
                                                    <Link to="/user">Users</Link>
                                                </li>
                                                <li>
                                                    <Link to="/category">Categories</Link>
                                                </li>
                                                <li>
                                                    <Link to="/book">Books</Link>
                                                </li>
                                                <li>
                                                    <Link to="/config">Configuration</Link>
                                                </li>
                                            </>
                                        )}
                                        {role === "Librarian" && (
                                            <>
                                                <li>
                                                    <Link to="/checkout">Checkout</Link>
                                                </li>
                                                <li>
                                                    <Link to="/return">Return</Link>
                                                </li>
                                            </>
                                        )}
                                        {role === "Profile" && (
                                            <>
                                                <li>
                                                    <Link to="/my_book">My books</Link>
                                                </li>
                                            </>
                                        )}
                                    </ul>
                                </nav>
                            </div>
                        </div>
                        <div className="col-lg-2">
                            <div className="header-action">
                                <Link to="/profile">My Profile</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default Header;