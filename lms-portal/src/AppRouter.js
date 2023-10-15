import Home from 'components/Home/Home';
import PageNotFound from 'components/PageNotFound/PageNotFound';
import { PrivateRoute } from 'components/PrivateRoute';
import { Property } from 'components/Property/Property';
import PropertyDetail from 'components/PropertyDetail/PropertyDetail';
import { SecuredPage } from 'components/SecuredPage';
import React from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import './App.css';
import PropertySearchList from './components/PropertyList/PropertySearchList';
import FavoriteProperty from "./components/FavoriteProperty/FavoriteProperty";
import Login from "./components/Login/Login";

function AppRouter() {
    let location = useLocation();
    const background = location.state && location.state.background;

    return (
        <div>
            <Routes location={background || location}>
                <Route exact path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/property-list" element={<PropertySearchList />} />
                <Route path="/property-detail/:id" element={<PropertyDetail open={true} />} />
                <Route path="/property" element={<Property />} />
                <Route path="/favorites" element={<FavoriteProperty />} />
                <Route path="/secured" element={
                    <PrivateRoute>
                        <SecuredPage />
                    </PrivateRoute>
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
