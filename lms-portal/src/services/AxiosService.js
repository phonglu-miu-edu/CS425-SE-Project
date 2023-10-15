import { getToken } from 'Utils';
import axios from 'axios';
import Constants from 'Constants';

const authService = axios.create({
    baseURL: Constants.AUTH_URL
});

authService.interceptors.request.use(
    (config) => {
        // const {origin} = new URL(config.url, Constants.AUTH_URL);
        // const allowedOrigin = [Constants.AUTH_URL];
        // const token = getToken();
        //
        // if (token && allowedOrigin.includes(origin)) {
        //     config.headers = {Authorization: `Bearer ${token}`};
        // }

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

const axiosInstance = {
    authService
};

export default axiosInstance;