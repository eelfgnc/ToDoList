import React from 'react';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import { styled } from '@mui/material/styles';
import IconButton from '@mui/material/IconButton';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import Divider from '@mui/material/Divider';
import { Calculate, Checklist, History, People } from '@mui/icons-material';
import RolePageConstant from '../constants/RolePageConstant';
import { useSelector } from 'react-redux';

interface SideBarProps {
    open: boolean;
    onClose: () => void;
  }
  
const drawerWidth = 240;

const DrawerHeader = styled('div')(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(0, 1),
    ...theme.mixins.toolbar,
    justifyContent: 'flex-end',
}));

const sideBarItemList = [
  {
    link:"/todo-list",
    label:"ToDo List",
    icon: <Checklist />,
    permission:[RolePageConstant.TODO_LIST]
  },
  {
    link:"/history-of-task",
    label:"History Of Task",
    icon: <History />,
    permission:[RolePageConstant.TODO_LIST]
  },
  {
    link:"/analyze",
    label:"Analyze",
    icon: <Calculate />,
    permission:[RolePageConstant.TODO_LIST]
  },
  {
    link:"/user-list",
    label:"User List",
    icon: <People />,
    permission:[RolePageConstant.TODO_LIST]
  }
];

const SideBar = (props: SideBarProps) => {
  const userPermission:string[] = useSelector((state: any) => state.user.roles);

  const hasPermission = (permissions: string[]) => {
    return permissions.some((permission) => userPermission.includes(permission));
  };

  return (
      <Drawer
      sx={{
        width: drawerWidth,
        flexShrink: 0,
        '& .MuiDrawer-paper': {
          width: drawerWidth,
          boxSizing: 'border-box',
        },
      }}
      variant="persistent"
      anchor="left"
      open={props.open}
    >
      <DrawerHeader>
        <IconButton onClick={props.onClose}>
          <ChevronLeftIcon />
        </IconButton>
      </DrawerHeader>
      <Divider />
      <List>
        {sideBarItemList.map((item, index) => {
          // Only render the item if the user has permission
          if (hasPermission(item.permission)) {
            return (
              <ListItem
                button
                component="a" // Use <a> tag for navigation
                href={item.link} // Use the link property for the href
                key={index}
              >
                <ListItemIcon>{item.icon}</ListItemIcon>
                <ListItemText primary={item.label} />
              </ListItem>
            );
          }
          return null; // Return null if the user doesn't have permission
        })}
      </List>
    </Drawer>
  );
}

export default SideBar;