import React, { useState } from "react";
import { Box, CssBaseline, styled, Toolbar } from "@mui/material";
import TopBar from "./TopBar";
import SideBar from "./SideBar";

const drawerWidth = 240;

const Main = styled('main', { shouldForwardProp: (prop) => prop !== 'open' })<{
  open: boolean;
}>(({ theme, open }) => ({
  flexGrow: 1,
  padding: theme.spacing(3),
  transition: theme.transitions.create('margin', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  marginLeft: `-${drawerWidth}px`,
  ...(open && {
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.easeOut,
      duration: theme.transitions.duration.enteringScreen,
    }),
    marginLeft: 0,
  }),
}));

export default function MiniDrawer(props: any) {
    const [isSideBarOpen, setIsSideBarOpen] = useState<boolean>(false);

    const handleMenuClick = () => {
      setIsSideBarOpen(true);
    };
  
    const handleSideBarClose = () => {
      setIsSideBarOpen(false);
    };
  

	return (
    <Box sx={{ display: 'flex' }}>
			<CssBaseline />
			<TopBar onMenuClick={handleMenuClick} isSideBarOpen={isSideBarOpen} />
            <SideBar open={isSideBarOpen} onClose={handleSideBarClose} />
            <Main open={isSideBarOpen}>
                <Toolbar />
                {props.children}
            </Main>
			{/* <Content>
				<Toolbar/>
				{props.children}
			</Content> */}
		</Box>
	);
}

