import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { SnackbarCustom } from 'components/SnackbarCustom/SnackbarCustom';
import './MyBook.scss';

const MyBook = () => {
    const [checkInQueue, setCheckInQueue] = useState([]);
    const dispatch = useDispatch();
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);

    useEffect(() => {
        setCheckInQueue([{
            id: 1,
            bookTitle: 'Book 1',
            bookCopySeq: 'ABCD'
        }, {
            id: 2,
            bookTitle: 'Book 2',
            bookCopySeq: 'EFGH'
        }, {
            id: 3,
            bookTitle: 'Book 3',
            bookCopySeq: 'IJKL'
        }, {
            id: 4,
            bookTitle: 'Book 4',
            bookCopySeq: 'MNOP'
        }]);
    }, []);

    return (
        <div className="container checkIn">
            <h3>My books</h3>
            <div className="row">
                <div className="col-12">
                    <TableContainer component={Paper}>
                        <Table stickyHeader sx={{minWidth: 650}} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Book title</TableCell>
                                    <TableCell>Book copy seq</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {checkInQueue.map(row => <TableRow
                                    key={row.id}
                                    sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                >
                                    <TableCell component="th" scope="row">{row.bookTitle}</TableCell>
                                    <TableCell>{row.bookCopySeq}</TableCell>
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

export default MyBook;