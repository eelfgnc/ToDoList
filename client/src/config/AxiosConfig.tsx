import axios from "axios";
import { addNotification } from "../redux/notification-slice";
import { RefreshTokenDTO } from "../dto/RefreshTokenDTO";
import { store } from "../redux/store";
import checkLocalStorageTokens, { setTokensOnLocalStorage } from "../helper/Auth";
import { hideLoading, showLoading } from "../component/RxCircularProgress";

const baseURL = process.env.REACT_APP_BASE_URL;
const instance = axios.create({
	baseURL: baseURL,
	responseType: "json",
	headers: {
		"Content-Type": "application/json",
	},
});

var loadingProcessNumber = 0;
var reqControl = axios.CancelToken.source();

instance.interceptors.response.use(
	(response) => {
		return response;
	},
	(error) => {
		if (
			error.response?.data?.errorCode === "RX-103" &&
			checkLocalStorageTokens()
		) {
			api.removeAuthHeader();
			api.post("/api/refreshToken", {
				refreshToken: localStorage.getItem("refreshToken"),
			})
				.then((response: RefreshTokenDTO) => {
					setTokensOnLocalStorage(
						response.accessToken,
						response.refreshToken
					);
					api.setAuthHeader(response.accessToken);
					window.location.reload();
				})
				.catch(() => {
					localStorage.clear();
					api.removeAuthHeader();
					window.location.reload();
				});
		} else if (!axios.isCancel(error)) {
			store.dispatch(
				addNotification({
					message: error?.response?.data?.message,
					type: "error",
				})
			);
		}

		if (--loadingProcessNumber <= 0) {
			hideLoading();
		}
		return Promise.reject(error);
	}
);

const api = {
	get<T = any>(url: string, params?: any) {
		loadingProcessNumber++;
		showLoading();
		return instance
			.get<T>(url, { ...params, cancelToken: reqControl.token })
			.then((response) => {
				if (--loadingProcessNumber === 0) {
					hideLoading();
				}
				return response.data;
			});
	},

	post<T = any>(url: string, data: any, headers = { "content-type": ["application/json"] }) {
		loadingProcessNumber++;
		showLoading();
		return instance.post<T>(url, data).then((response) => {
			if (--loadingProcessNumber === 0) {
				hideLoading();
			}
			return response.data;
		});
	},

	put<T = any>(
		url: string,
		data: any,
		headers = { "content-type": ["application/json"] }
	) {
		loadingProcessNumber++;
		showLoading();
		return instance.put<T>(url, data, { headers }).then((response) => {
			if (--loadingProcessNumber === 0) {
				hideLoading();
			}
			return response.data;
		});
	},

	delete(url: string) {
		loadingProcessNumber++;
		showLoading();
		return instance.delete(url).then(() => {
			if (--loadingProcessNumber === 0) {
				hideLoading();
			}
			return true;
		});
	},

	setAuthHeader(accessToken: string | null) {
		if (accessToken && accessToken !== "undefined") {
			instance.defaults.headers.common[
				"Authorization"
			] = `Bearer ${accessToken}`;
		}
	},

	removeAuthHeader() {
		delete instance.defaults.headers.common["Authorization"];
	},

	cancelAllGetRequest() {
		reqControl?.cancel();
		reqControl = axios.CancelToken.source();
	},
};

export default api;
