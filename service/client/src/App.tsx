import React from 'react';
import './App.css';
import { Navigate, Route, Routes } from 'react-router-dom';
import WelcomePage from './pages/WelcomePage';
import Layout from './components/Layout';
import RolePageConstant from './constants/RolePageConstant';
import LoginPage from './pages/LoginPage';
import PrivateRoute from './components/PrivateRoute';
import TaskListPage from './pages/TaskListPage';
import UserRegistePage from './pages/UserRegisterPage';
import UserListPage from './pages/UserListPage';
import HistoryTaskListPage from './pages/HistoryTaskListPage';
import TaskAnalysisPage from './pages/TaskAnalysisPage';

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
            <PrivateRoute  path="/todo-list" scopes={[RolePageConstant.USER, RolePageConstant.ADMIN]}>
              <TaskListPage />
            </PrivateRoute>
          }
        />

        <Route
          path="/register"
          element={
            <PrivateRoute  path="/register" scopes={[]}>
              <UserRegistePage />
            </PrivateRoute>
          }
        />

      <Route
          path="/user-list"
          element={
            <PrivateRoute  path="/user-list" scopes={[RolePageConstant.ADMIN]}>
              <UserListPage />
            </PrivateRoute>
          }
        />

      <Route
          path="/history-of-task"
          element={
            <PrivateRoute  path="/user-list" scopes={[RolePageConstant.USER, RolePageConstant.ADMIN]}>
              <HistoryTaskListPage />
            </PrivateRoute>
          }
        />

      <Route
          path="/analyze"
          element={
            <PrivateRoute  path="/user-list" scopes={[RolePageConstant.ADMIN]}>
              <TaskAnalysisPage />
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
