import { createSlice } from "@reduxjs/toolkit";
import { VariantType } from "notistack";
var uniqid = require("unique-slug");

export interface NotificationModel {
	id: string;
	message: string;
	type: VariantType;
}

let emptyNotificationList: NotificationModel[] = [];

export const notificationSlice = createSlice({
	name: "notification",
	initialState: {
		notifications: emptyNotificationList,
	},
	reducers: {
		addNotification: (state, action) => {
			const notificationObject =
				typeof action.payload == "object"
					? {
							id: uniqid(),
							message: action.payload.message,
							type: action.payload.type,
					  }
					: {
							id: uniqid(),
							message: action.payload,
							type: "success",
					  };
			return {
				...state,
				notifications: [...state.notifications, notificationObject],
			};
		},
		removeNotification: (state, key) => {
			return {
				...state,
				notifications: state.notifications.filter(
					(notification: NotificationModel) => notification.id !== key.payload
				),
			};
		},
	},
});

export const { addNotification, removeNotification } =
	notificationSlice.actions;
