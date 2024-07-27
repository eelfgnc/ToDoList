import React from 'react';
import { Lock, Person } from "@mui/icons-material";
import { Button, Card, CardActions, CardContent, CardHeader, InputAdornment, TextField } from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from '../../config/AxiosConfig';
import { LoginDTO } from '../../dto/LoginDTO';
import { setTokensOnLocalStorage } from '../../helper/Auth';


export interface AuthResponse {
    token: string;
};

const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [passwordInfo, setPasswordInfo] = useState("");
    const navigate = useNavigate();

    const handleLogin = () => {
        const AuthenticationRequestDTO = {
            email: username,
            password: passwordInfo
        }
		api.post("/auth/login", AuthenticationRequestDTO).then(
			(response: LoginDTO) => {
				setTokensOnLocalStorage(
					response.accessToken,
					response.refreshToken
				);
				api.setAuthHeader(response.accessToken);
				window.location.reload();
			}
		);
	};
    const handleUserName = (e: any) =>{
        setUsername(e.target.value);
    }
    const handlePassword = (e: any) =>{
        setPasswordInfo(e.target.value);
    }

    return (
        <form>
            <Card>
                <CardHeader 
                    title= "ToDo List App"
                />
                <CardContent>
                    <TextField
                        variant="outlined"
                        label="Kullanıcı Adı"
                        margin="normal"
                        InputProps={{
                            startAdornment:(
                                <InputAdornment position="start">
                                    <Person color="action"/>
                                </InputAdornment>
                            )
                        }}
                        onChange={handleUserName}
                    />
                    <TextField
                        variant="outlined"
                        label="Kullanıcı Parola"
                        margin="normal"
                        InputProps={{
                            startAdornment:(
                                <InputAdornment position="start">
                                    <Lock color="action"/>
                                </InputAdornment>
                            )
                        }}
                        onChange={handlePassword}
                    />
                </CardContent>
                <CardActions>
                    <Button
                        variant="outlined"
                        size="large"
                        type="button"
                        onClick={handleLogin}
                    >
                        Giriş Yap
                    </Button>
                </CardActions>
            </Card>
        </form>
    );    
}
export default LoginPage;
