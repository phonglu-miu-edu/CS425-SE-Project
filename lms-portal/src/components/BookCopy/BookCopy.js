import { Button, Card, FormControl, FormLabel, InputLabel, MenuItem, Select, TextField } from '@mui/material';
import Grid from '@mui/material/Unstable_Grid2';
import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { getAllBookCopies, getAllBooks, getBook } from 'services/AdminService';
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

const BookCopy = () => {
    const [isEdit, setIsEdit] = useState(false);
    const [list, setList] = useState([]);
    const [books, setBooks] = useState([]);
    const [book, setBook] = useState({});
    const [id, setId] = useState('');
    const [status, setStatus] = useState('Available');
    const [statusDetail, setStatusDetail] = useState('');
    const dispatch = useDispatch();
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);

    useEffect(() => {
        dispatch(getAllBooks(''))
            .then(res => {
                if (res.payload && res.payload.status_code == 200) {
                    setBooks(res.payload.data);
                }
            });
    }, []);

    useEffect(() => {
        if (book.id) {
            dispatch(getAllBookCopies(book.id))
                .then(res => {
                    if (res.payload && res.payload.status_code == 200) {
                        setList(res.payload.data);
                    }
                });
        } else {
            setList([]);
        }
    }, [book]);

    const onBookChange = e => {
        const bookId = e.target.value;
        const book = books.find(c => c.id === bookId);
        setBook(book);
    }

    const onStatusChange = e => setStatus(e.target.value);

    const onStatusDetailChange = e => setStatusDetail(e.target.value);

    const onSubmit = e => {
        // if (status) {
        //     const obj = {
        //     };
        //
        //     dispatch(id ? editBookCopy({id, book: obj}) : createBookCopy(obj))
        //         .then(res => {
        //             if (res.payload && res.payload.status_code == 200) {
        //                 setAlertContent('Save successfully');
        //                 setOpenAlert(true);
        //                 resetForm();
        //                 search(searchText);
        //             }
        //         });
        // }
    };

    const onEditClick = bookCopy => {
        if (bookCopy) {
            setIsEdit(true);
            setId(bookCopy.id);
            setStatus(bookCopy.status);
            setStatusDetail(bookCopy.statusDetail);
        }
    }

    const onDeleteClick = (bookCopy) => {
        if (window.confirm(`Do you want to delete book copy ${bookCopy.id}?`)) {
            // dispatch(deleteBook(book.id))
            //     .then(res => {
            //         if (res.payload && res.payload.status_code == 200) {
            //             setAlertContent(`Book ${book.title} is deleted`);
            //             setOpenAlert(true);
            //             resetForm();
            //             search(searchText);
            //         }
            //     });
        }
    };

    const resetForm = () => {
        setIsEdit(false);
        setId(null);
        setStatus('Available');
        setStatusDetail('');
    };

    return (
        <div className="container">
            <h3>Book Copy Management</h3>
            <div className="row">
                <div className="col-md-3">
                    <Card variant="outlined">
                        <form className="form">
                            {book && (
                                <div className="row">
                                    <div className="col-md-12">
                                        <FormControl fullWidth className="form-control-field">
                                            <TextField
                                                disabled
                                                value={book.title}
                                                fullWidth />
                                        </FormControl>
                                    </div>
                                </div>
                            )}
                            <div className="row">
                                <div className="col-md-12">
                                    <FormControl fullWidth className="form-control-field">
                                        <InputLabel id="statusLabel">Status</InputLabel>
                                        <Select
                                            labelId="statusLabel"
                                            value={status}
                                            onChange={onStatusChange}
                                            label="Status">
                                            <MenuItem value="Available">Available</MenuItem>
                                            <MenuItem value="Borrowed">Borrowed</MenuItem>
                                            <MenuItem value="Deleted">Deleted</MenuItem>
                                        </Select>
                                    </FormControl>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            label="Status Detail" type="search"
                                            onChange={onStatusDetailChange}
                                            value={statusDetail}
                                            multiline
                                            rows={4}
                                            fullWidth />
                                    </FormControl>
                                </div>
                            </div>
                            {book.id && (
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
                            )}
                        </form>
                    </Card>
                </div>
                <div className="col-md-9">
                    <div style={{display: 'flex', flexDirection: 'row', alignItems: 'center', paddingBottom: '10px'}}>
                        <FormLabel style={{paddingRight: '10px', minWidth: '80px'}}>Book</FormLabel>
                        <Select value={book.id} onChange={onBookChange} fullWidth>
                            {books.map(book => <MenuItem value={book.id}>{book.title}</MenuItem>)}
                        </Select>
                    </div>
                    <TableContainer component={Paper}>
                        <Table stickyHeader sx={{minWidth: 650}} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Id</TableCell>
                                    <TableCell>Status</TableCell>
                                    <TableCell>Status detail</TableCell>
                                    <TableCell>&nbsp;</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {list.map(row => <TableRow
                                    key={row.id}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">{row.id}</TableCell>
                                    <TableCell>{row.status}</TableCell>
                                    <TableCell>{row.statusDetail}</TableCell>
                                    <TableCell>
                                        <ActionIcon icon={<EditIcon onClick={() => onEditClick(row)} />} />
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

export default BookCopy;