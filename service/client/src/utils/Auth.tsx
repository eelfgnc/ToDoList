export const setTokenOnLocalStorage = ( accessToken: string, refreshToken : string) => {
    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);
}

export const checkTokenOnLocalStorage = (): boolean => {
    if(localStorage.getItem("accessToken") != null && localStorage.getItem("refreshToken") != null)
        return true;
    else
        return false;
}