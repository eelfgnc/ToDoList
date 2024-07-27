import { Theme } from "@emotion/react";
import { CssBaseline, makeStyles } from "@mui/material";
import React from "react";

export default function MiniDrawer(props: any) {
	const classes = useStyles;
	const [open, setOpen] = React.useState<boolean>(false);

	const toggleDrawer = () => {
		setOpen((prev) => !prev);
	};

	return (
		<div className={classes.root}>
			<CssBaseline />
			<TopBar open={open} toggleDrawer={toggleDrawer} />
			<SideDrawer open={open} />
			<main className={classes.content}>
				<div className={classes.toolbar} />
				{props.children}
			</main>
		</div>
	);
}

const useStyles = makeStyles((theme: Theme) => ({
	root: {
		display: "flex",
	},
	content: {
		flexGrow: 1,
		padding: theme.spacing(3),
	},
	toolbar: {
		display: "flex",
		alignItems: "center",
		justifyContent: "flex-end",
		padding: theme.spacing(0, 1),
		...theme.mixins.toolbar,
	},
}));
