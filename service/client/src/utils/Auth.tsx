import { accessToken, refreshToken } from "../constants/AppConstant";

export const setTokenOnLocalStorage = ( accessTokenValue: string, refreshTokenValue : string) => {
    localStorage.setItem(accessToken, accessTokenValue);
    localStorage.setItem(refreshToken, refreshTokenValue);
}

export const checkTokenOnLocalStorage = (): boolean => {
    if(localStorage.getItem(accessToken) != null && localStorage.getItem(refreshToken) != null)
        return true;
    else
        return false;
}