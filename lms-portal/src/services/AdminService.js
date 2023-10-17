import { createAsyncThunk } from "@reduxjs/toolkit";
import axiosInstance from "./AxiosService";

//#region Configuration

export const editAllConfigs = createAsyncThunk(
    'configs/edit',
    async (configs) => {
        const result = await axiosInstance.adminService.put(`/lms/admin/configs`, configs, {withCredentials: true});
        return result.data;
    });

export const getAllConfigs = createAsyncThunk(
    'configs/getAll',
    async () => {
        const result = await axiosInstance.adminService.get(`/lms/admin/configs`, {withCredentials: true});
        return result.data;
    });

//#endregion

//#region CheckIn

export const deleteBook = createAsyncThunk(
    'books/delete',
    async (id) => {
        const result = await axiosInstance.adminService.delete(`/lms/admin/books/${id}`, {withCredentials: true});
        return result.data;
    });

export const createBook = createAsyncThunk(
    'books/edit',
    async (book) => {
        const result = await axiosInstance.adminService.post('/lms/admin/books', book, {withCredentials: true});
        return result.data;
    });

export const editBook = createAsyncThunk(
    'books/edit',
    async ({id, book}) => {
        const result = await axiosInstance.adminService.put(`/lms/admin/books/${id}`, book, {withCredentials: true});
        return result.data;
    });

export const getAllBooks = createAsyncThunk(
    'books/getAll',
    async (searchText) => {
        const result = await axiosInstance.adminService.get(`/lms/admin/books/search?q=${searchText}`, {withCredentials: true});
        return result.data;
    });

export const getBook = createAsyncThunk(
    'books/get',
    async (id) => {
        const result = await axiosInstance.adminService.get(`/lms/admin/books/${id}`, {withCredentials: true});
        return result.data;
    });

//#endregion

//#region CheckIn Copy

export const getAllBookCopies = createAsyncThunk(
    'bookCopies/getAll',
    (searchText) => {
        // const result = await axiosInstance.adminService.get(`/lms/admin/bookCopies/search?q=${searchText}`, {withCredentials: true});
        return {
            status_code: 200,
            data: [{
                id: 1,
                status: "Available",
                statusDetail: "Available"
            }, {
                id: 2,
                status: "Available",
                statusDetail: "Available"
            }, {
                id: 3,
                status: "Borrowed",
                statusDetail: "Borrowed"
            }, {
                id: 4,
                status: "Deleted",
                statusDetail: "Deleted"
            }]
        }
    });

export const getBookCopies = createAsyncThunk(
    'bookCopies/get',
    async (id) => {
        const result = await axiosInstance.adminService.get(`/lms/admin/bookCopies/${id}`, {withCredentials: true});
        return result.data;
    });

//#endregion

//#region Category

export const deleteCategory = createAsyncThunk(
    'categories/delete',
    async (id) => {
        const result = await axiosInstance.adminService.delete(`/lms/admin/book_categories/${id}`, {withCredentials: true});
        return result.data;
    });

export const createCategory = createAsyncThunk(
    'categories/edit',
    async (category) => {
        const result = await axiosInstance.adminService.post('/lms/admin/book_categories', category, {withCredentials: true});
        return result.data;
    });

export const editCategory = createAsyncThunk(
    'categories/edit',
    async ({id, category}) => {
        const result = await axiosInstance.adminService.put(`/lms/admin/book_categories/${id}`, category, {withCredentials: true});
        return result.data;
    });

export const getAllCategories = createAsyncThunk(
    'categories/getAll',
    async (searchText) => {
        const result = await axiosInstance.adminService.get(`/lms/admin/book_categories/search?q=${searchText}`, {withCredentials: true});
        return result.data;
    });

export const getCategory = createAsyncThunk(
    'categories/get',
    async (id) => {
        const result = await axiosInstance.adminService.get(`/lms/admin/book_categories/${id}`, {withCredentials: true});
        return result.data;
    });

//#endregion

//#region User

export const deleteUser = createAsyncThunk(
    'users/delete',
    async (id) => {
        const result = await axiosInstance.adminService.delete(`/lms/admin/users/${id}`, {withCredentials: true});
        return result.data;
    });

export const createUser = createAsyncThunk(
    'users/edit',
    async (user) => {
        const result = await axiosInstance.adminService.post('/lms/admin/users', user, {withCredentials: true});
        return result.data;
    });

export const editUser = createAsyncThunk(
    'users/edit',
    async ({id, user}) => {
        const result = await axiosInstance.adminService.put(`/lms/admin/users/${id}`, user, {withCredentials: true});
        return result.data;
    });

export const getAllUsers = createAsyncThunk(
    'users/getAll',
    async (searchText) => {
        const result = await axiosInstance.adminService.get(`/lms/admin/users/search?q=${searchText}`, {withCredentials: true});
        return result.data;
    });

export const getUser = createAsyncThunk(
    'users/get',
    async (id) => {
        const result = await axiosInstance.adminService.get(`/lms/admin/users/${id}`, {withCredentials: true});
        return result.data;
    });

//#endregion