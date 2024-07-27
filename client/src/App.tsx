import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import WelcomePage from './page/welcome/WelcomePage';
import PrivateRoute from './component/PrivateRoute';
import LoginPage from './page/login/Loginpage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route
          path="/dashboard"
          element={
            <PrivateRoute>
              <WelcomePage />
            </PrivateRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
