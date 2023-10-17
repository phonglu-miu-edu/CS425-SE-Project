import { Box, Button, Card, FormControl, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import Grid from '@mui/material/Unstable_Grid2';
import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { createUser, editUser, getUser } from 'services/AdminService';
import { SnackbarCustom } from 'components/SnackbarCustom/SnackbarCustom';

const Profile = ({currentUser}) => {
    const [id, setId] = useState('');
    const [username, setUsername] = useState('');
    const [role, setRole] = useState('User');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [address, setAddress] = useState('');
    const [status, setStatus] = useState('Active');
    const dispatch = useDispatch();
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);

    useEffect(() => {
        if (currentUser && currentUser.id) {
            setId(currentUser.id);
        }
    }, []);

    useEffect(() => {
        if (id) {
            dispatch(getUser(id))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        const user = res.payload.data;

                        if (user) {
                            setUsername(user.userName);
                            setRole(user.roleCd);
                            setFirstName(user.firstName);
                            setLastName(user.lastName);
                            setEmail(user.email);
                            setPhoneNumber(user.phoneNumber);
                            setAddress(user.address);
                            setStatus(user.status);
                        }
                    }
                });
        }
    }, [id]);

    const onFirstNameChange = e => setFirstName(e.target.value);

    const onLastNameChange = e => setLastName(e.target.value);

    const onEmailChange = e => setEmail(e.target.value);

    const onPhoneNumberChange = e => setPhoneNumber(e.target.value);

    const onAddressChange = e => setAddress(e.target.value);

    const onSubmit = e => {
        if (username) {
            const obj = {
                userName: username,
                roleCd: role,
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                status
            };

            dispatch(id ? editUser({id, user: obj}) : createUser(obj))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setAlertContent('Save successfully');
                        setOpenAlert(true);
                    }
                });
        }
    };

    return (
        <div className="container">
            <h3>My Profile</h3>
            <Box
                sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    p: 1,
                    m: 1,
                    bgcolor: 'background.paper',
                    borderRadius: 1
                }}
            >
                <div className="col-12 col-sm-6">
                    <Card variant="outlined">
                        <form className="form">
                            <div className="row">
                                <div className="col-md-6">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            disabled
                                            label="Username" type="search"
                                            value={username}
                                            fullWidth />
                                    </FormControl>
                                </div>
                                <div className="col-md-6">
                                    <FormControl fullWidth className="form-control-field">
                                        <InputLabel id="roleLabel">Role</InputLabel>
                                        <Select
                                            disabled
                                            labelId="roleLabel"
                                            value={role}
                                            label="Role">
                                            <MenuItem value="User">Student</MenuItem>
                                            <MenuItem value="Admin">Admin</MenuItem>
                                            <MenuItem value="Librarian">Librarian</MenuItem>
                                        </Select>
                                    </FormControl>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-6">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            label="First name" type="search"
                                            onChange={onFirstNameChange}
                                            value={firstName}
                                            fullWidth />
                                    </FormControl>
                                </div>
                                <div className="col-md-6">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            label="Last name" type="search"
                                            onChange={onLastNameChange}
                                            value={lastName}
                                            fullWidth />
                                    </FormControl>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-6">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            label="Email" type="search"
                                            onChange={onEmailChange}
                                            value={email}
                                            fullWidth />
                                    </FormControl>
                                </div>
                                <div className="col-md-6">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            label="Phone number" type="search"
                                            onChange={onPhoneNumberChange}
                                            value={phoneNumber}
                                            fullWidth />
                                    </FormControl>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            label="Address" type="search"
                                            onChange={onAddressChange}
                                            value={address}
                                            fullWidth />
                                    </FormControl>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <FormControl fullWidth className="form-control-field">
                                        <InputLabel id="statusLabel">Status</InputLabel>
                                        <Select
                                            disabled
                                            labelId="statusLabel"
                                            value={status}
                                            label="Status">
                                            <MenuItem value="Active">Active</MenuItem>
                                            <MenuItem value="Locked">Locked</MenuItem>
                                            <MenuItem value="Suspended">Suspended</MenuItem>
                                        </Select>
                                    </FormControl>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <Grid container justifyContent="center">
                                        <Button variant="contained" color="primary" onClick={onSubmit}>Update</Button>
                                    </Grid>
                                </div>
                            </div>
                        </form>
                    </Card>
                </div>
            </Box>
            <SnackbarCustom
                vertical="top"
                horizontal="right"
                open={openAlert}
                autoHideDuration={2000}
                severity="success"
                closed={() => setOpenAlert(!openAlert)}
            >{alertContent}</SnackbarCustom>
        </div>
    );
};

export default Profile;