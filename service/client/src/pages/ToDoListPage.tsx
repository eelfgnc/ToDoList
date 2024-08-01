import { MaterialReactTable } from "material-react-table";
import { useEffect, useMemo, useState } from "react";
import api from "../config/AxiosConfig";
import { useSelector } from "react-redux";
import { Box, Button, Checkbox, IconButton, Menu, MenuItem } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

import MoreVertIcon from '@mui/icons-material/MoreVert';
import ToDoAddPage from "./ToDoAddPage";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { ToDoItemDTO, ToDoItemInitialize } from "../dto/ToDoItemDTO";
import ToDoDeletePage from "./ToDoDeletePage";


const ToDoListPage = () => {
    const userId = useSelector((state: any) => state.user.id);
    const [dataList, setDataList] = useState<ToDoItemDTO[]>([]);
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);
    const [option, setOption] = useState<string>("Done");
    const [openAddPage, setOpenAddPage] = useState<boolean>(false);
    const [openDeletePage, setOpenDeletePage] = useState<boolean>(false);
    const [listUpdate, setListUpdate] = useState<boolean>(false);
    const [selectedItem, setSelectedItem] = useState<ToDoItemDTO>(ToDoItemInitialize);

    const handleClick = (event: React.MouseEvent<HTMLElement>) => {
      setAnchorEl(event.currentTarget);
    };
  
    const handleClose = () => {
      setAnchorEl(null);
    };
  
    const handleMenuItemClick = (action: string) => {
        setOption(action);
      handleClose();
    };
    

	const GetToDoList = async () => {		
		await api.get("/todo/user/" +  userId).then((users: ToDoItemDTO[]) => {
			setDataList(users);
		});
	};
	
    useEffect(() => {
		GetToDoList();
	}, []); // eslint-disable-line

    useEffect(() => {
		GetToDoList();        
	}, [option]); // eslint-disable-line

    useEffect(() => {
        if(listUpdate){
            setSelectedItem(ToDoItemInitialize);
            GetToDoList();
            setListUpdate(false);
        }
	}, [listUpdate]); // eslint-disable-line

    const columns = useMemo(
        () => [
            {
                accessorKey: 'item',
                header: 'Item',
                Cell: ({ row }: any) => (
                    <span style={{ textDecoration: row.original.isDone ? 'line-through' : 'none' }}>
                    {row.original.item}
                    </span>
                ),
            },
            {
                id: 'actions',
                header: '',
                Cell: ({ row }: any) => (
                    <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                    <Checkbox
                        checked={row.original.isDone}
                        onChange={() => handleCheckboxChange(row.original.id)}
                    />
                    <IconButton onClick={() => handleEdit(row.original)}>
                        <EditIcon color="warning" fontSize="medium"/>
                    </IconButton>
                    <IconButton onClick={() => handleDelete(row.original)}>
                        <DeleteIcon color="error" fontSize="medium"/>
                    </IconButton>
                </div>
                ),
            },
        ],
        [dataList]
    );
    
    const handleCheckboxChange = (id: number) => {
        console.log('Select item with id:', id);
    };

    const handleEdit = (toDoItemDTO: ToDoItemDTO) => {
        setSelectedItem(toDoItemDTO);
        setOpenAddPage(true);
    };

    const handleDelete = (toDoItemDTO: ToDoItemDTO) => {
        setSelectedItem(toDoItemDTO);
        setOpenDeletePage(true);
    };



    return (
        <div>
            <MaterialReactTable
                columns={columns}
                data={dataList}
                renderToolbarInternalActions={({ table }) => (
                    <Box display={"flex"} alignItems={"center"}>
                    <Button
                        variant="contained"
                        color="primary"
                        style={{ marginRight: "8px" }}
                        onClick={() => {
                            setOpenAddPage(true);
                        }}
                    >
                        Add ToDo
                    </Button>
                    <Button
                        variant="outlined"
                        startIcon={<MoreVertIcon />}
                        onClick={handleClick}
                    >
                        Menu
                    </Button>
                    <Menu
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                    >
                        <MenuItem onClick={() => handleMenuItemClick('All')}>All</MenuItem>
                        <MenuItem onClick={() => handleMenuItemClick('Done')}>Done</MenuItem>
                        <MenuItem onClick={() => handleMenuItemClick('Todo')}>Todo</MenuItem>
                    </Menu>
                    </Box>
                )}
            />
            <ToDoAddPage
                initializer={selectedItem}
                open= {openAddPage}
                handleClose={setOpenAddPage}
                setListUpdate={setListUpdate}
            />
            <ToDoDeletePage
                initializer={selectedItem}
                open= {openDeletePage}
                handleClose={setOpenDeletePage}
                setListUpdate={setListUpdate}
            />
        </div>
    );
};

export default ToDoListPage;