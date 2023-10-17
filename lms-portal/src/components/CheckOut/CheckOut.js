import { Button, Card, CardHeader, FormControl, TextField } from '@mui/material';
import Grid from '@mui/material/Unstable_Grid2';
import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
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
import './CheckOut.scss';

const CheckOut = () => {
    const [isEdit, setIsEdit] = useState(false);
    const [checkOutQueue, setCheckOutQueue] = useState([]);
    const [studentId, setStudentId] = useState('');
    const [id, setId] = useState('');
    const [bookId, setBookId] = useState('');
    const [bookCopySeq, setBookCopySeq] = useState('');
    const dispatch = useDispatch();
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);

    useEffect(() => {
    }, []);

    const onStudentIdChange = e => setStudentId(e.target.value);

    const onBookIdChange = e => setBookId(e.target.value);

    const onBookCopySeqChange = e => setBookCopySeq(e.target.value);

    const onSubmit = () => {
        if (bookId && bookCopySeq) {
            if (id) {
                const item = checkOutQueue.find(c => c.id === id);

                item.bookId = bookId;
                item.bookCopySeq = bookCopySeq;
            } else {
                const autoId = checkOutQueue.length + 1;
                checkOutQueue.push({
                    id: autoId,
                    bookId,
                    bookCopySeq
                });
            }

            setCheckOutQueue([...checkOutQueue]);

            resetForm();
        }
    };

    const onFinalSubmit = () => {
        if (studentId && checkOutQueue.length > 0) {
            setAlertContent(`CheckOut completed`);
            setOpenAlert(true);

            resetForm();
            setStudentId('');
            setCheckOutQueue([]);
        }
    };

    const onEditClick = id => {
        const item = checkOutQueue.find(c => c.id === id);

        if (item) {
            setIsEdit(true);
            setId(item.id);
            setBookId(item.bookId);
            setBookCopySeq(item.bookCopySeq);
        }
    };

    const onDeleteClick = id => {
        let tmp = checkOutQueue.filter(c => c.id !== id);
        setCheckOutQueue([...tmp]);
    };

    const resetForm = () => {
        setIsEdit(false);
        setId('');
        setBookId('');
        setBookCopySeq('');
    };

    return (
        <div className="container checkOut">
            <h3>CheckOut</h3>
            <div className="row">
                <div className="col-md-3">
                    <Card variant="outlined">
                        <CardHeader title="Student" />
                        <form className="form">
                            <div className="row">
                                <div className="col-md-12">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            disabled={isEdit}
                                            label="Student id" type="search"
                                            onChange={onStudentIdChange}
                                            value={studentId}
                                            fullWidth />
                                    </FormControl>
                                </div>
                            </div>
                        </form>
                    </Card>
                    <hr />
                    <Card variant="outlined">
                        <form className="form">
                            <div className="row">
                                <div className="col-md-12">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            label="Book id" type="search"
                                            onChange={onBookIdChange}
                                            value={bookId}
                                            fullWidth />
                                    </FormControl>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <FormControl fullWidth className="form-control-field">
                                        <TextField
                                            label="Book copy seq" type="search"
                                            onChange={onBookCopySeqChange}
                                            value={bookCopySeq}
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
                    <form className="form">
                        <div className="row">
                            <div className="col-md-12">
                                <Grid container justifyContent="center">
                                    <Button variant="contained" color="primary"
                                            onClick={onFinalSubmit}>CheckOut</Button>
                                </Grid>
                            </div>
                        </div>
                    </form>
                </div>
                <div className="col-md-9">
                    <TableContainer component={Paper}>
                        <Table stickyHeader sx={{minWidth: 650}} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Book id</TableCell>
                                    <TableCell>Book copy seq</TableCell>
                                    <TableCell>&nbsp;</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {checkOutQueue.map(row => <TableRow
                                    key={row.id}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">{row.bookId}</TableCell>
                                    <TableCell>{row.bookCopySeq}</TableCell>
                                    <TableCell>
                                        <ActionIcon icon={<EditIcon onClick={() => onEditClick(row.id)} />} />
                                        <ActionIcon icon={<CancelIcon onClick={() => onDeleteClick(row.id)} />} />
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

export default CheckOut;