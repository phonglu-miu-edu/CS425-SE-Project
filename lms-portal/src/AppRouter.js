import Home from 'components/Home/Home';
import PageNotFound from 'components/PageNotFound/PageNotFound';
import {PrivateAdminRoute, PrivateAnyRoute, PrivateLibrarianRoute, PrivateStudentRoute} from 'components/PrivateRoute';
import React from 'react';
import {Route, Routes, useLocation} from 'react-router-dom';
import './App.css';
import Login from './components/Login/Login';
import User from './components/User/User';
import Book from './components/Book/Book';
import BookCopy from './components/BookCopy/BookCopy';
import Category from './components/Category/Category';
import Config from './components/Config/Config';
import Profile from './components/Profile/Profile';
import CheckOut from './components/CheckOut/CheckOut';
import CheckIn from './components/CheckIn/CheckIn';
import MyBook from './components/MyBook/MyBook';

function AppRouter() {
    let location = useLocation();
    const background = location.state && location.state.background;

    return (
        <div className="main-area">
            <Routes location={background || location}>
                <Route exact path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/profile" element={
                    <PrivateAnyRoute>
                        <Profile />
                    </PrivateAnyRoute>
                } />
                <Route path="/book" element={
                    <PrivateAdminRoute>
                        <Book />
                    </PrivateAdminRoute>
                } />
                <Route path="/bookCopy" element={
                    <PrivateAdminRoute>
                        <BookCopy />
                    </PrivateAdminRoute>
                } />
                <Route path="/category" element={
                    <PrivateAdminRoute>
                        <Category />
                    </PrivateAdminRoute>
                } />
                <Route path="/config" element={
                    <PrivateAdminRoute>
                        <Config />
                    </PrivateAdminRoute>
                } />
                <Route path="/user" element={
                    <PrivateAdminRoute>
                        <User />
                    </PrivateAdminRoute>
                } />
                <Route path="/checkout" element={
                    <PrivateLibrarianRoute>
                        <CheckOut />
                    </PrivateLibrarianRoute>
                } />
                <Route path="/checkin" element={
                    <PrivateLibrarianRoute>
                        <CheckIn />
                    </PrivateLibrarianRoute>
                } />
                <Route path="/myBook" element={
                    <PrivateStudentRoute>
                        <MyBook />
                    </PrivateStudentRoute>
                } />
                <Route path="*" element={<PageNotFound />} />
            </Routes>
        </div>
    );
}

export default AppRouter;
