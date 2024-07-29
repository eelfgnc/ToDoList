import React from 'react';
import './App.css';
import { Navigate, Route, Routes } from 'react-router-dom';
import WelcomePage from './pages/WelcomePage';
import Layout from './components/Layout';
import RolePageConstant from './constants/RolePageConstant';
import LoginPage from './pages/LoginPage';
import PrivateRoute from './components/PrivateRoute';
import ToDoListPage from './pages/ToDoListPage';

const App = () => {

  const appRoute = (
    <Routes>
      <Route path="/" element={<Navigate to="/login" replace />} />
        <Route
          path="/welcome"
          element={
            <PrivateRoute path="/welcome" redirectPath="/login">
              <WelcomePage />
            </PrivateRoute>
          }
        />

        <Route
          path="/login"
          element={
            <PrivateRoute path="/login" redirectPath="/todo-list">
              <LoginPage />
            </PrivateRoute>
          }
        />

        <Route
          path="/todo-list"
          element={
            <PrivateRoute  path="/todo-list" scopes={[RolePageConstant.TODO_LIST]}>
              <ToDoListPage />
            </PrivateRoute>
          }
        />
     </Routes>
  )

  return (
    <div className="App">
      <Layout>{appRoute}</Layout>
    </div>
  );
}

export default App;
