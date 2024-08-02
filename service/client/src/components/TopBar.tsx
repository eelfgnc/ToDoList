import { AppBar, Grid, IconButton, ListItemIcon, styled, Toolbar, Typography } from "@mui/material";
import MenuItem from '@mui/material/MenuItem';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import { useState } from "react";
import { AccountCircle, Logout, PersonAdd } from "@mui/icons-material";
import { useSelector } from "react-redux";
import { store } from "../redux/store";
import { removeUserWithRole } from "../redux/slice/UserSlice";
import { useNavigate } from "react-router-dom";
import { checkTokenOnLocalStorage } from "../utils/Auth";

interface TopBarProps {
    onMenuClick: () => void;
    isSideBarOpen: boolean;
  }

const drawerWidth = 240;

const StyledAppBar = styled(AppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
    })<{
    open: boolean;
    }>(({ theme, open }) => ({
    transition: theme.transitions.create(['margin', 'width'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: `${drawerWidth}px`,
        transition: theme.transitions.create(['margin', 'width'], {
        easing: theme.transitions.easing.easeOut,
        duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));

const TopBar = (props: TopBarProps) =>{    
    const navigate = useNavigate();
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);

    const handleMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const signOut = () => {
        localStorage.clear();
        store.dispatch(removeUserWithRole());
        navigate("login");
        handleClose();
    }

    return (		
        <StyledAppBar  position="fixed" open={props.isSideBarOpen}>
            <Toolbar>
                <IconButton edge="start" color="inherit" aria-label="menu"  sx={{ mr: 2, ...(props.isSideBarOpen && { display: 'none' }) }} onClick={props.onMenuClick}>
                    <MenuIcon />
                </IconButton>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    ToDo App
                </Typography>
                {checkTokenOnLocalStorage() && (
                    <div>
                        <IconButton
                            size="large"
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={handleMenu}
                            color="inherit"
                        >
                            <AccountCircle fontSize="large"/>
                        </IconButton>
                        <Menu
                            id="menu-appbar"
                            anchorEl={anchorEl}
                            anchorOrigin={{
                            vertical: 'top',
                            horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin={{
                            vertical: 'top',
                            horizontal: 'right',
                            }}
                            open={Boolean(anchorEl)}
                            onClose={handleClose}
                        >
                            <MenuItem onClick={handleClose}>
                                <ListItemIcon>
                                    <PersonAdd
                                        fontSize="medium"
                                        color="success"
                                    />
                                </ListItemIcon>
                                Profile
                            </MenuItem>
                            <MenuItem onClick={signOut}>
                                <ListItemIcon>
                                    <Logout
                                        color="error"
                                        fontSize="medium"
                                    />
                                </ListItemIcon>
                                Sign Out
                            </MenuItem>
                        </Menu>
                    </div>
                )}
            </Toolbar>            
            
        </StyledAppBar>
    );
}

export default TopBar;