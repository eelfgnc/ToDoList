import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
	name: "user",
	initialState: {
		id: 0,
		username:"",
		adSoyad:"",
		permissions: [],
	},
	reducers: {
		setUserWithPermissions: (state, action) => {
			state.id = action.payload.id;
			state.username = action.payload.username;
			state.adSoyad = action.payload.adSoyad;
			state.permissions = action.payload.roleList;
		},
		removeUserPermissions: (state) => {
			state.permissions = [];
		},
	},
});

export const { setUserWithPermissions, removeUserPermissions } = userSlice.actions;
