import { createAsyncThunk } from '@reduxjs/toolkit';
import axiosInstance from './AxiosService';

export const login = createAsyncThunk(
    'auth/login',
    async (authCredential) => {
        try {
            const response = await axiosInstance.authService.post('/lms/auth/login', {
                userName: authCredential.username,
                password: authCredential.password
            }, {withCredentials: true});
            return response.data;
        } catch (err) {
            return err.response;
        }
    });


export const logout = createAsyncThunk(
    'auth/logout',
    async (authCredential) => {
        try {
            const response = await axiosInstance.authService.post('/lms/auth/logout', {
                userName: authCredential.username,
                roleCd: authCredential.role
            }, {withCredentials: true});
            return response.data;
        } catch (err) {
            return err.response;
        }
    });
