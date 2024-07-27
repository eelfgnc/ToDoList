import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { loadSlice } from "./load-slice";
import { notificationSlice } from "./notification-slice";
import { userSlice } from "./user-slice";
export const store = configureStore({
	reducer: combineReducers({
		notifications: notificationSlice.reducer,
		user: userSlice.reducer,
		load: loadSlice.reducer,
	}),
	devTools: process.env.NODE_ENV !== "production",
});
