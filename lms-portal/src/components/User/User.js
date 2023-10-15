import {
    Button,
    Card,
    FormControl,
    FormLabel,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from '@mui/material';
import { debounce } from 'lodash';
import Grid from '@mui/material/Unstable_Grid2';
import React, { useCallback, useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { createUser, deleteUser, editUser, getAllUsers, getUser } from 'services/AdminService';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import ActionIcon from 'components/ActionIcon/ActionIcon';
import EditIcon from '@mui/icons-material/Edit';
import CancelIcon from '@mui/icons-material/Cancel';
import { SnackbarCustom } from 'components/SnackbarCustom/SnackbarCustom';

const Profile = () => {
    const [list, setList] = useState([]);
    const [searchText, setSearchText] = useState('');
    const [id, setId] = useState('');
    const [username, setUsername] = useState('');
    const [role, setRole] = useState('User');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [address, setAddress] = useState('');
    const [numOfOverdues, setNumOfOverdues] = useState(0);
    const [status, setStatus] = useState('Active');
    const dispatch = useDispatch();
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);

    useEffect(() => search(), []);

    useEffect(() => {
        search(searchText);
    }, [searchText]);

    const getSearchResults = useCallback(
        debounce(value =>
            dispatch(getAllUsers(value))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setList(res.payload.data);
                    }
                }), 800), []
    );

    const search = searchText => {
        getSearchResults(searchText);
    }

    const onSearchTextChange = e => setSearchText(e.target.value);

    const onUsernameChange = e => setUsername(e.target.value);

    const onRoleChange = e => setRole(e.target.value);

    const onFirstNameChange = e => setFirstName(e.target.value);

    const onLastNameChange = e => setLastName(e.target.value);

    const onEmailChange = e => setEmail(e.target.value);

    const onPhoneNumberChange = e => setPhoneNumber(e.target.value);

    const onAddressChange = e => setAddress(e.target.value);

    const onStatusChange = e => setStatus(e.target.value);

    const onSubmit = e => {
        if (username) {
            const user = {
                id,
                userName: username,
                roleCd: role,
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                status
            };

            dispatch(id ? editUser({id, user}) : createUser(user))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setAlertContent(res.payload.message);
                        setOpenAlert(true);
                        resetForm();
                        search(searchText);
                    }
                });
        }
    };

    const onEditClick = id =>
        dispatch(getUser(id))
            .then(res => {
                if (res.payload && res.payload.status_code == 200) {
                    const user = res.payload.data;

                    if (user) {
                        setId(user.id);
                        setUsername(user.userName);
                        setRole(user.roleCd);
                        setFirstName(user.firstName);
                        setLastName(user.lastName);
                        setEmail(user.email);
                        setPhoneNumber(user.phoneNumber);
                        setAddress(user.address);
                        setStatus(user.status);
                        setNumOfOverdues(user.numOfOverdues);
                    }
                }
            });

    const onDeleteClick = (user) => {
        if (window.confirm(`Do you want to delete user ${user.userName}?`)) {
            dispatch(deleteUser(user.id))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setAlertContent(`User ${user.userName} is deleted`);
                        setOpenAlert(true);
                        resetForm();
                        search(searchText);
                    }
                });
        }
    }

    const resetForm = () => {
        setId(null);
        setUsername('');
        setRole('User');
        setFirstName('');
        setLastName('');
        setEmail('');
        setPhoneNumber('');
        setAddress('');
        setStatus('Active');
        setNumOfOverdues(0);
    };

    const isEdit = id != null && id != undefined;

    return <div className="container">
        <h3>Profile Management</h3>
        <div className="row">
            <div className="col-md-4">
                <Card variant="outlined">
                    <form className="form">
                        <div className="row">
                            <div className="col-md-6">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        disabled={isEdit}
                                        label="Username" type="search"
                                        onChange={onUsernameChange}
                                        value={username}
                                        fullWidth />
                                </FormControl>
                            </div>
                            <div className="col-md-6">
                                <FormControl fullWidth className="form-control-field">
                                    <InputLabel id="roleLabel">Role</InputLabel>
                                    <Select
                                        labelId="roleLabel"
                                        value={role}
                                        label="Role"
                                        onChange={onRoleChange}>
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
                                        labelId="statusLabel"
                                        value={status}
                                        label="Status"
                                        onChange={onStatusChange}>
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
                                    {!isEdit && <Button variant="contained" color="primary" onClick={onSubmit}>Add</Button>}
                                    {isEdit && (
                                        <>
                                            <Button variant="contained" color="primary" onClick={onSubmit} style={{marginRight: '10px'}}>Update</Button>
                                            <Button onClick={resetForm}>Cancel</Button>
                                        </>
                                    )}
                                </Grid>
                            </div>
                        </div>
                    </form>
                </Card>
            </div>
            <div className="col-md-8">
                <div style={{display: 'flex', flexDirection: 'row', alignItems: 'center', paddingBottom: '10px'}}>
                    <FormLabel style={{paddingRight: '10px', minWidth: '80px'}}>Search</FormLabel>
                    <TextField fullWidth value={searchText} onChange={onSearchTextChange} />
                </div>
                <TableContainer component={Paper}>
                    <Table stickyHeader sx={{minWidth: 650}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>Username</TableCell>
                                <TableCell>First name</TableCell>
                                <TableCell>Last name</TableCell>
                                <TableCell>Role</TableCell>
                                <TableCell>Overdue amount</TableCell>
                                <TableCell>Status</TableCell>
                                <TableCell>&nbsp;</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {list.map(row => <TableRow
                                key={row.id}
                                sx={{'&:last-child td, &:last-child th': {border: 0}}}
                            >
                                <TableCell component="th" scope="row">
                                    {row.userName}
                                </TableCell>
                                <TableCell>{row.firstName}</TableCell>
                                <TableCell>{row.lastName}</TableCell>
                                <TableCell>{row.roleCd}</TableCell>
                                <TableCell>{row.numOfOverdues || 0}</TableCell>
                                <TableCell>{row.status}</TableCell>
                                <TableCell>
                                    <ActionIcon icon={<EditIcon onClick={() => onEditClick(row.id)} />} />
                                    <ActionIcon icon={<CancelIcon onClick={() => onDeleteClick(row)} />} />
                                </TableCell>
                            </TableRow>)}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </div>
        <SnackbarCustom
            vertical="top"
            horizontal="right"
            open={openAlert}
            autoHideDuration={2000}
            severity="success"
            closed={() => setOpenAlert(!openAlert)}
        >{alertContent}</SnackbarCustom>
    </div>;
};

export default Profile;