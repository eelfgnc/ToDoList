import { createSlice } from "@reduxjs/toolkit";

interface UserState {
    id: number,
    userName: string,
    name: string,
    roles: string[]
}
  
const initialStateUser: UserState = {
    id: 0,
    userName: "",
    name: "",
    roles: []
};
  
export const userSlice = createSlice({
    name: "user",
    initialState: initialStateUser,
    reducers:{
        setUserWithRole: (state, action) => {
            console.log("set slice: ", action.payload);
            state.id = action.payload.id;
            state.userName = action.payload.userName;
            state.name = action.payload.name;
            state.roles = action.payload.roles;
        },
        removeUserWithRole: (state) => {
            state.roles = []
        }
    }
});


export const { setUserWithRole, removeUserWithRole } =
	userSlice.actions;
