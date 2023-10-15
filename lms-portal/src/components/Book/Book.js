import { Button, Card, FormControl, InputLabel, MenuItem, Pagination, Select, TextField } from "@mui/material";
import Grid from "@mui/material/Unstable_Grid2";
import { useEffect, useState } from "react";
import DataPagination from "../DataPagination/DataPagination";
import { useDispatch } from "react-redux";
import { getAllBooks } from "services/AdminService";

export default function Book(props) {
    let {users, itemPerPage} = props;
    users = users || [];

    let [page, setPage] = useState(1);
    const [id, setId] = useState(null);
    const [title, setTitle] = useState('');
    const [isbn, setIsbn] = useState('');
    const [authors, setAuthors] = useState('');
    const count = Math.ceil(users.length / itemPerPage);
    const DATA_PAGINATION = DataPagination(users, itemPerPage);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getAllBooks());
    }, []);

    useEffect(() => {
        gotoPage(1);
    }, [users]);

    const onTitleChange = (e) => {
        setTitle(e.target.value);
    }

    const onIsbnChange = (e) => {
        setIsbn(e.target.value);
    }

    const onAuthorsChange = (e) => {
        setAuthors(e.target.value);
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
            <h3>Book Management</h3>
            <div className="col-md-4">
                <Card variant="outlined">
                    <form className="form">
                        <div className="row">
                            <div className="col-12">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        label="Title" type="search"
                                        onChange={onTitleChange}
                                        value={title}
                                        fullWidth />
                                </FormControl>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        label="ISBN" type="search"
                                        onChange={onIsbnChange}
                                        value={isbn}
                                        fullWidth />
                                </FormControl>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        label="Authors" type="search"
                                        onChange={onAuthorsChange}
                                        value={authors}
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