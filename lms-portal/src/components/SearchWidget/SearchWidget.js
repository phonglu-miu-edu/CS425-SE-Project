import './SearchWidget.scss';
import { Button, Grid, TextField } from '@mui/material';
import { useState } from 'react';
import { useNavigate } from 'react-router';

const SearchWidget = () => {
    const nav = useNavigate();
    const [name, setName] = useState('');

    const textChange = (e) => {
        let txt = e.target.value;
        if (txt) {
            setName(txt.trim());
        }
    };

    const keyDown = (e) => {
        if (e.keyCode === 13) {
            searchClick(e);
        }
    };

    const searchClick = (e) => {
        e.preventDefault();

        const searchParams = new URLSearchParams();

        if (name !== '') {
            searchParams.append('name', name);
        }

        let url = '/search?' + searchParams.toString();
        nav(url);
    };

    return (
        <div className="searchWidget">
            <div className="property-search-header">
                <h3>Find your knowledge</h3>
            </div>
            <Grid container padding={2}>
                <Grid item xs={12} sx={{mb: 2}}>
                    <TextField
                        fullWidth
                        placeholder="Any text"
                        id="bookInput"
                        variant="outlined"
                        onChange={textChange}
                        onKeyDown={keyDown}>
                    </TextField>
                </Grid>
                <Grid item xs={12} sx={{textAlign: 'center'}}>
                    <Button variant="contained" onClick={searchClick}>Search</Button>
                </Grid>
            </Grid>
        </div>
    );
};

export default SearchWidget;