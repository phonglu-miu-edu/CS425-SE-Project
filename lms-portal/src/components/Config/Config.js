import { Box, Button, Card, FormControl, TextField } from '@mui/material';
import Grid from '@mui/material/Unstable_Grid2';
import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { editAllConfigs, getAllConfigs } from 'services/AdminService';
import { SnackbarCustom } from 'components/SnackbarCustom/SnackbarCustom';

const Config = () => {
    const [configs, setConfigs] = useState([]);
    const dispatch = useDispatch();
    const [alertContent, setAlertContent] = useState('');
    const [openAlert, setOpenAlert] = useState(false);

    useEffect(() => {
        getAll();
    }, []);

    const getAll = () => {
        dispatch(getAllConfigs())
            .then(res => {
                if (res.payload && res.payload.status_code == 200) {
                    setConfigs(res.payload.data);
                }
            });
    };

    const onConfigChange = config => e => {
        config.itemValue = e.target.value;

        setConfigs([...configs]);
    };

    const onSubmit = () => {
        dispatch(editAllConfigs(configs))
            .then(res => {
                if (res.payload && res.payload.status_code == 200) {
                    setAlertContent('Save successfully');
                    setOpenAlert(true);
                    getAll();
                }
            });
    };

    return (
        <div className="container">
            <h3>Configuration</h3>
            <Box
                sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    p: 1,
                    m: 1,
                    bgcolor: 'background.paper',
                    borderRadius: 1
                }}
            >
                <div className="col-12 col-md-6">
                    <Card variant="outlined">
                        <form className="form">
                            <div className="row">
                                <div className="col-md-12">
                                    {configs.map(config => (
                                        <FormControl key={config.id} fullWidth className="form-control-field">
                                            <TextField
                                                label={config.itemName}
                                                onChange={onConfigChange(config)}
                                                value={config.itemValue}
                                                fullWidth />
                                        </FormControl>
                                    ))}
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <Grid container justifyContent="center">
                                        <Button variant="contained" color="primary" onClick={onSubmit}>Update</Button>
                                    </Grid>
                                </div>
                            </div>
                        </form>
                    </Card>
                </div>
            </Box>
            <SnackbarCustom
                vertical="top"
                horizontal="right"
                open={openAlert}
                autoHideDuration={2000}
                severity="success"
                closed={() => setOpenAlert(!openAlert)}
            >{alertContent}</SnackbarCustom>
        </div>
    );
};

export default Config;