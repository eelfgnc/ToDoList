import {CardMedia, Grid, Paper, Typography } from "@mui/material";
import AlertImages from "../assets/images/alert.png";
import { Link } from "react-router-dom";

const ForbiddenPage = () => {
    return (
        <Paper style={{	width: "50%",
            minWidth: "500px",
            padding: "40px",
            marginTop: "32px",
            marginBottom: "8px",
            marginLeft: "auto",
            marginRight: "auto"}}>
            <Grid
                container
                spacing={1}
                direction="row"
                justifyContent="center"
                alignItems="center"
                alignContent="center"
            >
                <Grid item xs={2}>
                    <CardMedia
                        component="img"
                        sx={{
                            width: 150,
                            height: 150,
                            margin: "20px auto",
                            borderRadius: "0%",
                        }}
                        image={AlertImages}
                        alt="App Logo"
                    />
                </Grid>
                <Grid item xs={8}>
                    <Grid>
                        <Grid>
                            <Typography variant="h5" align="center">You are not authorized to view this area!</Typography>
                        </Grid>
                        <Grid>
                            <Link to={"http://localhost:3000/login"}><b>Return To Login Page!</b></Link>
                        </Grid>
                    </Grid>
                </Grid>
                <Grid item xs={2}>
                <CardMedia
                        component="img"
                        sx={{
                            width: 150,
                            height: 150,
                            margin: "20px auto",
                            borderRadius: "0%",
                        }}
                        image={AlertImages}
                        alt="App Logo"
                    />
                </Grid>
            </Grid>
        </Paper>
    )
};

export default ForbiddenPage;