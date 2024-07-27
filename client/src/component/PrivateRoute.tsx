import React from 'react';
import { Navigate, Route } from "react-router-dom";

interface PrivateRouteProps {
  children: JSX.Element;
}

function PrivateRoute({ children }: PrivateRouteProps) {
  const token = localStorage.getItem('token');
  return token ? children : <Navigate to="/login" />;
}

export default PrivateRoute;
