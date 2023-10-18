import { createAsyncThunk } from "@reduxjs/toolkit";
import axiosInstance from "./AxiosService";

//#region MyBook

export const checkOut = createAsyncThunk(
    'books/checkOut',
    async (checkOutQueue) => {
        const result = await axiosInstance.bookService.post(`/lms/book/checkout`, checkOutQueue, {withCredentials: true});
        return result.data;
    });

//#endregion