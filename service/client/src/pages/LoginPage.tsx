import { Button, Card, CardActions, CardContent, CardHeader, CardMedia, InputAdornment, TextField, Typography } from "@mui/material";
import { Lock, Person } from "@mui/icons-material";
import { setTokenOnLocalStorage } from "../utils/Auth";
import { LoginDTO } from "../dto/LoginDTO";
import api from "../config/AxiosConfig";
import { useState } from "react";
import { Link } from "react-router-dom";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import AppImages from "../assets/images/to-do-list.png";

const LoginPage = () => {
	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");

    const handleLogin = () => {
		api.post("/auth/login", { "email":username, "password":password })
        .then(
			(response: LoginDTO) => {
				setTokenOnLocalStorage(
					response.accessToken,
					response.refreshToken
				);
				api.setAuthHeader(response.accessToken);
				window.location.reload();
                //navigate('/todo-list');
			}
		)
        .catch(()=>{
            store.dispatch(addNotification({message: "You can't log in. Check your username and password.", type:"error"}));
        });
	};

    const handleUsernameChange = (e: any) => {
		setUsername(e.target.value);
	};

	const handlePasswordChange = (e: any) => {
		setPassword(e.target.value);
	};

    return (
            <form autoComplete="off" style={{paddingTop: "100px"}}>
                <Card sx={{ maxWidth: 600 }} >
                    <CardMedia
                        component="img"
                        sx={{
                        width: 150,
                        height: 150,
                        margin: "20px auto",
                        borderRadius: "0%",
                        }}
                        image={AppImages}
                        alt="App Logo"
                    />
                    <CardHeader
                        title="Sign in to ToDo App"
                    />
                    <CardContent>
                        <div
                            onKeyDown={(e) => {
                                if (e.key === "Enter") {
                                    handleLogin();
                                }
                            }}
                        >
                            <TextField
                                required
                                fullWidth
                                variant="outlined"
                                id="username"
                                type="text"
                                label="Username"
                                margin="normal"
                                onChange={handleUsernameChange}
                                InputProps={{
                                    startAdornment: (
                                        <InputAdornment position="start">
                                            <Person color="action" />
                                        </InputAdornment>
                                    ),
                                }}
                            />
                            <TextField
                                required
                                fullWidth
                                variant="outlined"
                                id="password"
                                type="password"
                                label="Password"
                                margin="normal"
                                onChange={handlePasswordChange}
                                InputProps={{
                                    startAdornment: (
                                        <InputAdornment position="start">
                                            <Lock color="action" />
                                        </InputAdornment>
                                    ),
                                }}
                            />
                        </div>
                    </CardContent>
                    <CardActions style={{display:"flex", alignItems:"center", justifyContent:"center"}}>
                        <Button
                            variant="contained"
                            size="large"
                            type="button"
                            onClick={handleLogin}
                        >
                            SIGN IN
                        </Button>
                    </CardActions>
                    <CardContent>
                        <Link to={"http://localhost:3000/register"}><b>Create an account!</b></Link>
                    </CardContent>
                </Card>
            </form>
    );
}

export default LoginPage;