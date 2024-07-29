import React from 'react';
import ReactDOM from 'react-dom/client'; // React 18 ile birlikte
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { SnackbarProvider } from 'notistack';
import { BrowserRouter } from 'react-router-dom'; // HashRouter yerine BrowserRouter kullanılması önerilir
import { store } from './redux/store';
import { checkTokenOnLocalStorage } from './utils/Auth';
import api from './config/AxiosConfig';
import { UserRoleDTO } from './dto/UserRoleDTO';
import { setUserWithRole } from './redux/slice/UserSlice';
import { CircularProgress, createTheme, ThemeProvider } from '@mui/material';

const container = document.getElementById('root');
const root = ReactDOM.createRoot(container!); // React 18 kullanımı


const theme = createTheme({
  typography: {
    fontSize: 11.5,
  },
});

function render() {
  if (checkTokenOnLocalStorage()) {
		const tokenString = localStorage.getItem("accessToken");
		api.setAuthHeader(tokenString);
		if (
			tokenString !== undefined &&
			tokenString !== null &&
			tokenString !== ""
		) {
			api.get<UserRoleDTO>("/users/roles").then(
				(userRoleDTO: UserRoleDTO) => {
          console.log("user info: ", userRoleDTO);
					store.dispatch(setUserWithRole(userRoleDTO));
				}
			);
		}
	}

  root.render(
    <Provider store={store}>
      <SnackbarProvider maxSnack={5} anchorOrigin={{ vertical: 'top', horizontal: 'right' }}>
        <CircularProgress />
        {/* <Notification /> */}
        <BrowserRouter>
          <ThemeProvider theme={theme}>
            <App />
          </ThemeProvider>
        </BrowserRouter>
      </SnackbarProvider>
    </Provider>
  );
}

render();

if (process.env.NODE_ENV === 'development' && module.hot) {
  module.hot.accept('./App', render);
}

reportWebVitals();
