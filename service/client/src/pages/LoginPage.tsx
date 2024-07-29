import { Button, Card, CardActions, CardContent, CardHeader, InputAdornment, TextField, Typography } from "@mui/material";
import { Lock, Person } from "@mui/icons-material";
import { setTokenOnLocalStorage } from "../utils/Auth";
import { LoginDTO } from "../dto/LoginDTO";
import api from "../config/AxiosConfig";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
    const navigate = useNavigate();
	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");

    const handleLogin = () => {
		api.post("/auth/login", { "email":username, "password":password }).then(
			(response: LoginDTO) => {
				setTokenOnLocalStorage(
					response.accessToken,
					response.refreshToken
				);
				api.setAuthHeader(response.accessToken);
				window.location.reload();
                //navigate('/todo-list');
			}
		);
	};

    const handleUsernameChange = (e: any) => {
		setUsername(e.target.value);
	};

	const handlePasswordChange = (e: any) => {
		setPassword(e.target.value);
	};

    return (
        <form autoComplete="off">
        <Card>
            <CardHeader
                title="ToDo App"
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
                        label="Kullanıcı Adı"
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
                        label="Parola"
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
            <CardActions>
                <Button
                    variant="contained"
                    size="large"
                    type="button"
                    onClick={handleLogin}
                >
                    GİRİŞ YAP
                </Button>
            </CardActions>
        </Card>
    </form>
    )
}

export default LoginPage;