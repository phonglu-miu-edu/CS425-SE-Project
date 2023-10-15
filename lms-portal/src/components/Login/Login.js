import React, { useState } from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import "./Login.scss";
import { useNavigate } from "react-router";
import { useDispatch } from 'react-redux';
import { login } from "services/AuthService";
import { SnackbarCustom } from "components/SnackbarCustom/SnackbarCustom";

export default function Login() {
    let settings = {
        dots: true,
        speed: 300,
        draggable: false,
        lazyLoad: 'ondemand'
    };

    const nav = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);
    const dispatch = useDispatch();

    const keyDown = (e) => {
        if (e.keyCode === 13) {
            onLoginClick(e);
        }
    };

    function onUsernameChange(e) {
        setUsername(e.target.value)
    }

    function onPasswordChange(e) {
        setPassword(e.target.value);
    }

    const onLoginClick = (e) => {
        e.preventDefault();

        if (username && password) {
            dispatch(login({username, password}))
                .then(async (response) => {
                    nav('/');
                })
                .catch((error) => {
                    setOpenAlert(true);
                    if (error.message) {
                        setAlertContent(error.message);
                    } else {
                        setAlertContent('You have not permission for this feature!');
                    }
                });
        } else {
            setOpenAlert(true);
            setAlertContent('Please fill in');
        }
    };

    return (
        <div className='container-fluid login'>
            <Slider {...settings}>
                <div>
                    <img className="pic" src="/slider-1.jpg" alt="slider-1" />
                </div>
                <div>
                    <img className="pic" src="/slider-2.jpg" alt="slider-2" />
                </div>
            </Slider>
            <div className="slider-property-search">
                <div className="property-search-header">
                    <h3>Authentication</h3>
                </div>
                <div className="property-search-body">
                    <div className="row">
                        <div className="col-12">
                            <p>
                                <input type="text" placeholder="Username" onChange={onUsernameChange}
                                       onKeyDown={keyDown} />
                            </p>
                        </div>
                        <div className="col-12">
                            <p>
                                <input type="password" placeholder="Password" onChange={onPasswordChange}
                                       onKeyDown={keyDown} />
                            </p>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-12">
                            <p>
                                <button type="button" onClick={onLoginClick}>Login</button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <SnackbarCustom
                vertical='top'
                horizontal='right'
                open={openAlert}
                autoHideDuration={2000}
                severity="success"
                closed={() => setOpenAlert(!openAlert)}
            >{alertContent}</SnackbarCustom>
        </div>
    );
}