import { Button, Card, FormControl, InputLabel, MenuItem, Pagination, Select, TextField } from "@mui/material";
import Grid from "@mui/material/Unstable_Grid2";
import { useEffect, useState } from "react";
import DataPagination from "../DataPagination/DataPagination";
import { useDispatch } from "react-redux";
import { getAllCategories } from "services/AdminService";

export default function Config(props) {
    let {users, itemPerPage} = props;
    users = users || [];

    let [page, setPage] = useState(1);
    const [id, setId] = useState(null);
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const count = Math.ceil(users.length / itemPerPage);
    const DATA_PAGINATION = DataPagination(users, itemPerPage);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getAllCategories());
    }, []);

    useEffect(() => {
        gotoPage(1);
    }, [users]);

    const onNameChange = (e) => {
        setName(e.target.value);
    }

    const onDescriptionChange = (e) => {
        setDescription(e.target.value);
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
            <h3>Configuration</h3>
            <div className="col-md-4">
                <Card variant="outlined">
                    <form className="form">
                        <div className="row">
                            <div className="col-md-6">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        label="First name" type="search"
                                        onChange={onNameChange}
                                        value={name}
                                        fullWidth />
                                </FormControl>
                            </div>
                            <div className="col-md-6">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        label="Last name" type="search"
                                        onChange={onDescriptionChange}
                                        value={description}
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