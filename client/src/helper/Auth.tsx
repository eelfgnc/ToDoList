export const accessTokenString = "accessToken";
export const refreshTokenString = "refreshToken";

export const checkLocalStorageTokens = (): boolean => {
	return (
		localStorage.getItem(accessTokenString) !== null &&
		localStorage.getItem(refreshTokenString) !== null
	);
};

export const setTokensOnLocalStorage = (
	accessToken: string,
	refreshToken: string
) => {
	localStorage.setItem(accessTokenString, accessToken);
	localStorage.setItem(refreshTokenString, refreshToken);
};

export default checkLocalStorageTokens;
