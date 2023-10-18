import axios from 'axios';
import Constants from 'Constants';

const adminService = axios.create({
    baseURL: Constants.ADMIN_URL
});

const authService = axios.create({
    baseURL: Constants.AUTH_URL
});

const bookService = axios.create({
    baseURL: Constants.BOOK_URL
});

const axiosInstance = {
    adminService,
    authService,
    bookService
};

export default axiosInstance;