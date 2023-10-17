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
import './CheckIn.scss';

const CheckIn = () => {
    const [isEdit, setIsEdit] = useState(false);
    const [checkInQueue, setCheckInQueue] = useState([]);
    const [studentId, setStudentId] = useState('');
    const dispatch = useDispatch();
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);

    useEffect(() => {
    }, []);

    const onStudentIdChange = e => setStudentId(e.target.value);

    const onQueryClick = () => {
        if (studentId) {
            setCheckInQueue([{
                id: 1,
                bookId: "1",
                bookCopySeq: "ABCD"
            }, {
                id: 2,
                bookId: "2",
                bookCopySeq: "EFGH"
            }, {
                id: 3,
                bookId: "3",
                bookCopySeq: "IJKL"
            }, {
                id: 4,
                bookId: "4",
                bookCopySeq: "MNOP"
            }]);
        }
    }

    const onSubmit = () => {
        if (studentId) {
            setAlertContent(`CheckIn completed`);
            setOpenAlert(true);

            setStudentId('');
            setCheckInQueue([]);
        }
    };

    const onEditClick = id => {
        const item = checkInQueue.find(c => c.id === id);

        if (item) {
            setIsEdit(true);
        }
    };

    const onDeleteClick = id => {
        let tmp = checkInQueue.filter(c => c.id !== id);
        setCheckInQueue([...tmp]);
    };

    return (
        <div className="container checkIn">
            <h3>CheckIn</h3>
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
                            <div className="row">
                                <div className="col-md-12">
                                    <Grid container justifyContent="center">
                                        <Button variant="contained" color="primary"
                                            onClick={onQueryClick}>Query</Button>
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
                                            onClick={onSubmit}>CheckIn</Button>
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
                                {checkInQueue.map(row => <TableRow
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

export default CheckIn;