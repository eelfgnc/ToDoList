import { MaterialReactTable, MRT_ColumnDef, MRT_ToggleDensePaddingButton, MRT_ToggleFullScreenButton, useMaterialReactTable } from "material-react-table";
import { useEffect, useMemo, useState } from "react";
import api from "../config/AxiosConfig";
import { useSelector } from "react-redux";
import { Box, Button, Checkbox, IconButton, Menu, MenuItem } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import PrintIcon from '@mui/icons-material/Print';

import MoreVertIcon from '@mui/icons-material/MoreVert';
type ToDoItem = {
    id: number;
    item: string;
    isDone: boolean;
  };

const ToDoListPage = () => {
    const userId = useSelector((state: any) => state.user.id);
    const [dataList, setDataList] = useState<ToDoItem[]>([]);

    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);
  
    const handleClick = (event: React.MouseEvent<HTMLElement>) => {
      setAnchorEl(event.currentTarget);
    };
  
    const handleClose = () => {
      setAnchorEl(null);
    };
  
    const handleMenuItemClick = (action: string) => {
      console.log('Menu action:', action);
      handleClose();
    };
    
	useEffect(() => {
		GetToDoList();
	}, []); // eslint-disable-line

	const GetToDoList = async () => {		
		await api.get("/todo/user/" +  userId).then((users: ToDoItem[]) => {
			setDataList(users);
		});
	};

 
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
                    <IconButton onClick={() => handleEdit(row.original.id)}>
                        <EditIcon color="warning" fontSize="medium"/>
                    </IconButton>
                    <IconButton onClick={() => handleDelete(row.original.id)}>
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

    const handleEdit = (id: number) => {
        console.log('Edit item with id:', id);
    };

    const handleDelete = (id: number) => {
        console.log('Delete item with id:', id);
    };



    return (
        <div>
            <MaterialReactTable
                columns={columns}
                data={dataList}
                renderTopToolbarCustomActions={() => (
                    <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
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
                        <MenuItem onClick={() => handleMenuItemClick('action1')}>Action 1</MenuItem>
                        <MenuItem onClick={() => handleMenuItemClick('action2')}>Action 2</MenuItem>
                        <MenuItem onClick={() => handleMenuItemClick('action3')}>Action 3</MenuItem>
                    </Menu>
                    </div>
                )}
                renderToolbarInternalActions={({ table }) => (
                    <Box>
                    <IconButton
                        onClick={() => {
                        window.print();
                        }}
                    >
                        <PrintIcon />
                    </IconButton>

                    <MRT_ToggleDensePaddingButton table={table} />
                    <MRT_ToggleFullScreenButton table={table} />

                    <IconButton
                        onClick={handleClick}
                        aria-controls="simple-menu"
                        aria-haspopup="true"
                    >
                        <MoreVertIcon />
                    </IconButton>
                    <Menu
                        id="simple-menu"
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                    >
                        <MenuItem onClick={() => handleMenuItemClick('action1')}>Action 1</MenuItem>
                        <MenuItem onClick={() => handleMenuItemClick('action2')}>Action 2</MenuItem>
                        <MenuItem onClick={() => handleMenuItemClick('action3')}>Action 3</MenuItem>
                    </Menu>
                    </Box>
                )}
            />
        </div>
    );
};

export default ToDoListPage;