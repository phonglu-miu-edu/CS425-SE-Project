import Home from 'components/Home/Home';
import PageNotFound from 'components/PageNotFound/PageNotFound';
import { PrivateAnyRoute, PrivateAdminRoute } from 'components/PrivateRoute';
import { Property } from 'components/Property/Property';
import PropertyDetail from 'components/PropertyDetail/PropertyDetail';
import React from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import './App.css';
import PropertySearchList from './components/PropertyList/PropertySearchList';
import FavoriteProperty from "./components/FavoriteProperty/FavoriteProperty";
import Login from "./components/Login/Login";
import User from "./components/User/User";
import Book from "./components/Book/Book";
import BookCopy from "./components/BookCopy/BookCopy";
import Category from "./components/Category/Category";
import Config from "./components/Config/Config";
import Profile from "./components/Profile/Profile";

function AppRouter() {
    let location = useLocation();
    const background = location.state && location.state.background;

    return (
        <div className="main-area">
            <Routes location={background || location}>
                <Route exact path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/property-list" element={<PropertySearchList />} />
                <Route path="/property-detail/:id" element={<PropertyDetail open={true} />} />
                <Route path="/property" element={<Property />} />
                <Route path="/favorites" element={<FavoriteProperty />} />
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
                <Route path="*" element={<PageNotFound />} />
            </Routes>
            {background && (
                <Routes>
                    <Route path="/property-detail/:id" element={<PropertyDetail open={true} />} />
                </Routes>
            )}
        </div>
    );
}

export default AppRouter;
