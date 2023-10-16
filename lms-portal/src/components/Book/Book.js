import { Button, Card, FormControl, FormLabel, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import { debounce } from 'lodash';
import Grid from '@mui/material/Unstable_Grid2';
import React, { useCallback, useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { createBook, deleteBook, editBook, getAllBooks, getAllCategories, getBook } from 'services/AdminService';
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
    const [isEdit, setIsEdit] = useState(false);
    const [list, setList] = useState([]);
    const [categories, setCategories] = useState([]);
    const [firstCategoryId, setFirstCategoryId] = useState(1);
    const [searchText, setSearchText] = useState('');
    const [id, setId] = useState('');
    const [title, setTitle] = useState('');
    const [isbn, setIsbn] = useState('');
    const [authors, setAuthors] = useState('');
    const [numOfCopies, setNumOfCopies] = useState(0);
    const [bookCategoryId, setBookCategoryId] = useState(0);
    const dispatch = useDispatch();
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);

    useEffect(() => {
        search();

        dispatch(getAllCategories(''))
            .then(res => {
                if (res.payload && res.payload.status_code == 200) {
                    const categories = res.payload.data;

                    setCategories(categories);

                    if (categories.length > 0) {
                        if (!isEdit) {
                            setBookCategoryId(categories[0].id);
                        }

                        setFirstCategoryId(categories[0].id);
                    }
                }
            });
    }, []);

    useEffect(() => {
        search(searchText);
    }, [searchText]);

    const getSearchResults = useCallback(
        debounce(value =>
            dispatch(getAllBooks(value))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setList(res.payload.data);
                    }
                }), 800), []
    );

    const search = searchText => {
        getSearchResults(searchText);
    };

    const onSearchTextChange = e => setSearchText(e.target.value);

    const onTitleChange = e => setTitle(e.target.value);

    const onIsbnChange = e => setIsbn(e.target.value);

    const onAuthorsChange = e => setAuthors(e.target.value);

    const onCategoryChange = e => setBookCategoryId(e.target.value);

    const onSubmit = e => {
        if (title) {
            const obj = {
                title,
                isbn,
                authors,
                bookCategoryId: bookCategoryId
            };

            dispatch(id ? editBook({id, book: obj}) : createBook(obj))
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
        dispatch(getBook(id))
            .then(res => {
                if (res.payload && res.payload.status_code == 200) {
                    const book = res.payload.data;

                    if (book) {
                        setIsEdit(true);
                        setId(book.id);
                        setTitle(book.title);
                        setIsbn(book.isbn);
                        setAuthors(book.authors);
                        setBookCategoryId(book.bookCategoryId);
                    }
                }
            });

    const onDeleteClick = (book) => {
        if (window.confirm(`Do you want to delete book ${book.title}?`)) {
            dispatch(deleteBook(book.id))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setAlertContent(`Book ${book.title} is deleted`);
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
        setTitle('');
        setIsbn('');
        setAuthors('');
        setBookCategoryId(firstCategoryId);
    };

    const getBookCategoryName = (categoryId) => {
        const category = categories.find(c => c.id === categoryId);

        return category ? category.categoryName : '';
    }

    return <div className="container">
        <h3>Book Management</h3>
        <div className="row">
            <div className="col-md-3">
                <Card variant="outlined">
                    <form className="form">
                        <div className="row">
                            <div className="col-md-12">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        disabled={isEdit}
                                        label="Title" type="search"
                                        onChange={onTitleChange}
                                        value={title}
                                        fullWidth />
                                </FormControl>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-md-6">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        label="Isbn" type="search"
                                        onChange={onIsbnChange}
                                        value={isbn}
                                        fullWidth />
                                </FormControl>
                            </div>
                            <div className="col-md-6">
                                <FormControl fullWidth className="form-control-field">
                                    <InputLabel id="categoryLabel">Category</InputLabel>
                                    <Select
                                        labelId="categoryLabel"
                                        value={bookCategoryId}
                                        label="Category"
                                        onChange={onCategoryChange}>
                                        {categories.map(category => <MenuItem
                                            value={category.id}>{category.categoryName}</MenuItem>)}
                                    </Select>
                                </FormControl>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-md-12">
                                <FormControl fullWidth className="form-control-field">
                                    <TextField
                                        label="Author" type="search"
                                        onChange={onAuthorsChange}
                                        value={authors}
                                        fullWidth />
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
            <div className="col-md-9">
                <div style={{display: 'flex', flexDirection: 'row', alignItems: 'center', paddingBottom: '10px'}}>
                    <FormLabel style={{paddingRight: '10px', minWidth: '80px'}}>Search</FormLabel>
                    <TextField fullWidth value={searchText} onChange={onSearchTextChange} />
                </div>
                <TableContainer component={Paper}>
                    <Table stickyHeader sx={{minWidth: 650}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>Title</TableCell>
                                <TableCell>Isbn</TableCell>
                                <TableCell>Authors</TableCell>
                                <TableCell>Number of copies</TableCell>
                                <TableCell>Book category</TableCell>
                                <TableCell>&nbsp;</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {list.map(row => <TableRow
                                key={row.id}
                                sx={{'&:last-child td, &:last-child th': {border: 0}}}
                            >
                                <TableCell component="th" scope="row">{row.title}</TableCell>
                                <TableCell>{row.isbn}</TableCell>
                                <TableCell>{row.authors}</TableCell>
                                <TableCell>{row.numOfCopies}</TableCell>
                                <TableCell>{getBookCategoryName(row.bookCategoryId)}</TableCell>
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