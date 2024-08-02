import { useSnackbar, VariantType } from "notistack";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import { store } from "../redux/store";
import { NotificationModel, removeNotification } from "../redux/slice/NotificationSlice";
import { Button } from "@mui/material";

let displayed: any[] = [];

const addDisplayed = (id: any) => {
	displayed = [...displayed, id];
};

const removeDisplayed = (id: any) => {
	displayed = [...displayed.filter((key) => id !== key)];
};

const Notification = () => {
	const { enqueueSnackbar, closeSnackbar } = useSnackbar();
	const notifications = useSelector(
		(store: any) => store.notification.notifications || []
	);
	useEffect(() => {
		notifications.forEach((notification: NotificationModel) => {
			if (displayed.includes(notification.id)) return;
			if (notification.message === undefined || notification.message === null || notification.message.trim() === "") return;

			enqueueSnackbar(notification.message, {
				key: notification.id,
				variant: notification.type as VariantType,
				onExited: (event, key) => {
					store.dispatch(removeNotification(key));
					removeDisplayed(key);
				},
				action: (key) => (
					<Button onClick={() => closeSnackbar(key)}>X</Button>
				),
			});

			addDisplayed(notification.id);
		});
	}, [notifications]); // eslint-disable-line

	return null;
};

export default Notification;
