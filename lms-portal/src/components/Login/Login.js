import React, { useState } from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import './Login.scss';
import { useNavigate } from 'react-router';
import { useDispatch } from 'react-redux';
import { login } from 'services/AuthService';
import { SnackbarCustom } from 'components/SnackbarCustom/SnackbarCustom';
import { useCookies } from 'react-cookie';
import Constants from 'Constants';
import { Button, FormControl, TextField } from '@mui/material';

export default function Login() {
    let settings = {
        dots: true,
        speed: 300,
        draggable: false,
        lazyLoad: 'ondemand'
    };

    const [_, setCookie] = useCookies([Constants.COOKIE.LMS_DATA]);
    const nav = useNavigate();
    const [username, setUsername] = useState('john');
    const [password, setPassword] = useState('123');
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);
    const dispatch = useDispatch();

    const keyDown = (e) => {
        if (e.keyCode === 13) {
            onLoginClick(e);
        }
    };

    function onUsernameChange(e) {
        setUsername(e.target.value);
    }

    function onPasswordChange(e) {
        setPassword(e.target.value);
    }

    const onLoginClick = (e) => {
        e.preventDefault();

        if (username && password) {
            dispatch(login({username, password}))
                .then(async (response) => {
                    if (response && response.payload) {
                        if (response.payload.status_code === 200) {
                            setCookie(Constants.COOKIE.LMS_DATA, JSON.stringify(response.payload.data));

                            nav('/');
                        } else {
                            const data = response.payload.data;

                            if (data) {
                                setOpenAlert(true);

                                const message = data.message;
                                setAlertContent(message ? message : 'You have not permission for this feature!');

                                return;
                            }
                        }
                    }

                    setOpenAlert(true);
                    setAlertContent('There is something wrong with authentication');
                });
        } else {
            setOpenAlert(true);
            setAlertContent('Please fill in');
        }
    };

    return (
        <div className="container-fluid login">
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
                    <form className="form">
                        <div className="row">
                            <div className="col-12">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        label="Username" type="search"
                                        onChange={onUsernameChange}
                                        onKeyDown={keyDown}
                                        fullWidth />
                                </FormControl>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        label="Password"
                                        onChange={onPasswordChange}
                                        onKeyDown={keyDown}
                                        fullWidth
                                        type="password" />
                                </FormControl>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-md-6">
                                <Button variant="contained" color="primary" fullWidth>Register</Button>
                            </div>
                            <div className="col-md-6">
                                <Button variant="contained" color="primary" fullWidth
                                        onClick={onLoginClick}>Login</Button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <SnackbarCustom
                vertical="top"
                horizontal="right"
                open={openAlert}
                severity="error"
                autoHideDuration={2000}
                closed={() => setOpenAlert(!openAlert)}
            >{alertContent}</SnackbarCustom>
        </div>
    );
}