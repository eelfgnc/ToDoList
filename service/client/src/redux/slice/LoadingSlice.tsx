import { createSlice } from "@reduxjs/toolkit";

export const loadingSlice = createSlice({
	name: "load",
	initialState: {
		isLoading: false,
	},
	reducers: {
		setLoading: (state, action) => {
			state.isLoading = action.payload.isLoading;
		},
	},
});

export const { setLoading } = loadingSlice.actions;
