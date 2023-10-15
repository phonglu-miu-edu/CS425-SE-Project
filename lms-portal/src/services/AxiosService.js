import axios from 'axios';
import Constants from 'Constants';

const adminService = axios.create({
    baseURL: Constants.ADMIN_URL
});

const authService = axios.create({
    baseURL: Constants.AUTH_URL
});

const axiosInstance = {
    adminService,
    authService
};

export default axiosInstance;