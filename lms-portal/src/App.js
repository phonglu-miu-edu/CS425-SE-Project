import Footer from 'components/Footer/Footer';
import Header from 'components/Header/Header';
import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import './App.css';
import AppRouter from './AppRouter';

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Header />
                <AppRouter />
                <Footer />
            </BrowserRouter>
        </div>
    );
}

export default App;
