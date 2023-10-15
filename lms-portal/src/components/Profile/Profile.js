import { Button, Card, FormControl, InputLabel, MenuItem, Pagination, Select, TextField } from "@mui/material";
import Grid from "@mui/material/Unstable_Grid2";
import { useEffect, useState } from "react";
import DataPagination from "../DataPagination/DataPagination";
import { useDispatch } from "react-redux";
import { getAllUsers } from "services/AdminService";

export default function Profile(props) {
    let {users, itemPerPage} = props;
    users = users || [];

    let [page, setPage] = useState(1);
    const [username, setUsername] = useState('');
    const [role, setRole] = useState('Profile');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const count = Math.ceil(users.length / itemPerPage);
    const DATA_PAGINATION = DataPagination(users, itemPerPage);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getAllUsers());
    }, []);

    useEffect(() => {
        gotoPage(1);
    }, [users]);

    const onUsernameChange = (e) => {
        setUsername(e.target.value);
    }

    const onRoleChange = (e) => {
        setRole(e.target.value);
    }

    const onFirstNameChange = (e) => {
        setFirstName(e.target.value);
    }

    const onLastNameChange = (e) => {
        setLastName(e.target.value);
    }

    const gotoPage = (page) => {
        setPage(page);
        DATA_PAGINATION.goTo(page);
    }

    const pageChanged = (e, page) => {
        gotoPage(page);
    }

    const handleDelete = (id) => {
        if (typeof props.setRefresh === "function") {
            props.setRefresh(true);
        }
    }

    return (
        <div className="container">
            <h3>Profile Management</h3>
            <div className="col-md-4">
                <Card variant="outlined">
                    <form className="form">
                        <div className="row">
                            <div className="col-md-6">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
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
                                        <MenuItem value="Profile">Student</MenuItem>
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
                            <div className="col-md-12">
                                <Grid container justifyContent="center">
                                    <Button variant="contained" color="primary">Save</Button>
                                </Grid>
                            </div>
                        </div>
                    </form>
                </Card>
            </div>
            <Grid container rowSpacing={3} columnSpacing={{xs: 1, sm: 2, md: 3}}>
                {DATA_PAGINATION.currentData().map(prop =>
                    <Grid key={prop.id} xs={12} md={6} lg={4} xl={3}>
                        {/*<PropertyCard {...prop} roles={roles} {...others} deletedFunc={() => handleDelete(prop.id)} />*/}
                    </Grid>
                )}
            </Grid>
            {count > 0 && (
                <div className="pagination">
                    <Pagination
                        count={count}
                        size="large"
                        color="primary"
                        page={page}
                        onChange={pageChanged}
                    />
                </div>
            )}
        </div>
    )
}