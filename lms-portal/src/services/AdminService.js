import { createAsyncThunk } from "@reduxjs/toolkit";
import axiosInstance from "./AxiosService";

export const getAllBooks = createAsyncThunk(
    'books/getAll',
    async () => {
        const result = await axiosInstance.adminService.get('/lms/admin/books/search?q=', {withCredentials: true});
        return result.data;
    });

export const getAllCategories = createAsyncThunk(
    'categories/getAll',
    async () => {
        const result = await axiosInstance.adminService.get('/lms/admin/book_categories/search?q=', {withCredentials: true});
        return result.data;
    });

export const editUser = createAsyncThunk(
    'users/edit',
    async ({id, user}) => {
        const result = await axiosInstance.adminService.put('/lms/admin/users/' + id, user, {withCredentials: true});
        return result.data;
    });

export const getAllUsers = createAsyncThunk(
    'users/getAll',
    async (searchText) => {
        const result = await axiosInstance.adminService.get('/lms/admin/users/search?q=' + searchText, {withCredentials: true});
        return result.data;
    });

export const getUser = createAsyncThunk(
    'users/getById',
    async (id) => {
        const result = await axiosInstance.adminService.get('/lms/admin/users/' + id, {withCredentials: true});
        return result.data;
    });