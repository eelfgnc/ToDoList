import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { userSlice } from "./slice/UserSlice";
import { notificationSlice } from "./slice/NotificationSlice";
import { loadingSlice } from "./slice/LoadingSlice";

export const store = configureStore({
    reducer: combineReducers({
        user: userSlice.reducer,
        notification: notificationSlice.reducer,
        loading: loadingSlice.reducer
    }),
    devTools: process.env.NODE_ENV !== "production"
});