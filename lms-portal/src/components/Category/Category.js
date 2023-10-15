import { Button, Card, FormControl, FormLabel, TextField } from '@mui/material';
import { debounce } from 'lodash';
import Grid from '@mui/material/Unstable_Grid2';
import React, { useCallback, useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { createCategory, deleteCategory, editCategory, getAllCategories, getCategory } from 'services/AdminService';
import TableContainer from '@mui/material/TableContainer';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import TableBody from '@mui/material/TableBody';
import ActionIcon from 'components/ActionIcon/ActionIcon';
import EditIcon from '@mui/icons-material/Edit';
import CancelIcon from '@mui/icons-material/Cancel';
import { SnackbarCustom } from 'components/SnackbarCustom/SnackbarCustom';

const Category = () => {
    const [isEdit, setIsEdit] = useState(false);
    const [list, setList] = useState([]);
    const [searchText, setSearchText] = useState('');
    const [id, setId] = useState('');
    const [categoryName, setCategoryName] = useState('');
    const [description, setDescription] = useState('');
    const dispatch = useDispatch();
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);

    useEffect(() => search(), []);

    useEffect(() => {
        search(searchText);
    }, [searchText]);

    const getSearchResults = useCallback(
        debounce(value =>
            dispatch(getAllCategories(value))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setList(res.payload.data);
                    }
                }), 800), []);

    const search = searchText => {
        getSearchResults(searchText);
    };

    const onSearchTextChange = e => setSearchText(e.target.value);

    const onCategoryNameChange = e => setCategoryName(e.target.value);

    const onDescriptionChange = e => setDescription(e.target.value);

    const onSubmit = e => {
        if (categoryName) {
            const obj = {
                id,
                categoryName,
                description
            };

            dispatch(id ? editCategory({id, category: obj}) : createCategory(obj))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setAlertContent('Save successfully');
                        setOpenAlert(true);
                        resetForm();
                        search(searchText);
                    }
                });
        }
    };

    const onEditClick = id =>
        dispatch(getCategory(id))
            .then(res => {
                if (res.payload && res.payload.status_code == 200) {
                    const obj = res.payload.data;

                    if (obj) {
                        setIsEdit(true);
                        setId(obj.id);
                        setCategoryName(obj.categoryName);
                        setDescription(obj.description);
                    }
                }
            });

    const onDeleteClick = (user) => {
        if (window.confirm(`Do you want to delete category ${user.categoryName}?`)) {
            dispatch(deleteCategory(user.id))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setAlertContent(`Category ${user.categoryName} is deleted`);
                        setOpenAlert(true);
                        resetForm();
                        search(searchText);
                    }
                });
        }
    };

    const resetForm = () => {
        setIsEdit(false);
        setId(null);
        setCategoryName('');
        setDescription('');
    };

    return (
        <div className="container">
            <h3>Category Management</h3>
            <div className="row">
                <div className="col-md-4">
                    <Card variant="outlined">
                        <form className="form">
                            <div className="row">
                                <div className="col-md-12">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            label="Category name" type="search"
                                            onChange={onCategoryNameChange}
                                            value={categoryName}
                                            fullWidth />
                                    </FormControl>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            id="outlined-multiline-static"
                                            label="Description"
                                            multiline
                                            rows={4}
                                            onChange={onDescriptionChange}
                                            value={description}
                                        />
                                    </FormControl>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <Grid container justifyContent="center">
                                        {!isEdit &&
                                            <Button variant="contained" color="primary" onClick={onSubmit}>Add</Button>}
                                        {isEdit && (
                                            <>
                                                <Button variant="contained" color="primary" onClick={onSubmit}
                                                        style={{marginRight: '10px'}}>Update</Button>
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
                                    <TableCell>Category name</TableCell>
                                    <TableCell>Description</TableCell>
                                    <TableCell>&nbsp;</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {list.map(row => <TableRow
                                    key={row.id}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}>
                                    <TableCell component="th" scope="row">{row.categoryName}</TableCell>
                                    <TableCell>{row.description}</TableCell>
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
        </div>
    );
};

export default Category;