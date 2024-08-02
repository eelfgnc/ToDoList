import { MaterialReactTable } from "material-react-table";
import { useEffect, useMemo, useState } from "react";
import api from "../config/AxiosConfig";
import { useSelector } from "react-redux";
import { Box, Button, Checkbox, IconButton, Menu, MenuItem } from "@mui/material";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { Add, DeleteForever, DeleteOutline, Edit, Delete, MoreVert } from "@mui/icons-material";
import { TaskDTO, TaskInitialize } from "../dto/TaskDTO";
import TaskAddPage from "./TaskAddPage";
import TaskDeletePage from "./TaskDeletePage";
import TaskAllDeletePage from "./TaskAllDeletePage";

const TaskListPage = () => {
    const userId = useSelector((state: any) => state.user.id);
    const [dataList, setDataList] = useState<TaskDTO[]>([]);
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);
    const [option, setOption] = useState<string>("All");
    const [openAddPage, setOpenAddPage] = useState<boolean>(false);
    const [openDeletePage, setOpenDeletePage] = useState<boolean>(false);
    const [openAllDeletePage, setOpenAllDeletePage] = useState<boolean>(false);
    const [listUpdate, setListUpdate] = useState<boolean>(false);
    const [selectedItem, setSelectedItem] = useState<TaskDTO>(TaskInitialize);
    const [deleteType, setDeleteType] = useState<string>("");

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

	const GetTaskList = async () => {
		await api.get("/task/user/" +  userId + "?type=" + option).then((taskDTO: TaskDTO[]) => {
			setDataList(taskDTO);
		}).catch(() => {
            store.dispatch(addNotification({message: "An error occurred while loading the data. Please try again later.", type: "error"}));
        });
	};

    useEffect(() => {
		GetTaskList();
	}, []); // eslint-disable-line

    useEffect(() => {
		GetTaskList();
	}, [option]); // eslint-disable-line

    useEffect(() => {
        if(listUpdate){
            setSelectedItem(TaskInitialize);
            GetTaskList();
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
                        onChange={() => handleCheckboxChange(row.original, !row.original.isDone)}
                    />
                    <IconButton  aria-hidden aria-label="edit item" onClick={() => handleEdit(row.original)}>
                        <Edit color="warning" fontSize="medium"/>
                    </IconButton>
                    <IconButton  aria-hidden aria-label="delete item" onClick={() => handleDelete(row.original)}>
                        <Delete color="error" fontSize="medium"/>
                    </IconButton>
                </div>
                ),
            },
        ],
        [dataList]
    );

    const handleCheckboxChange = (taskDTO: TaskDTO, isDone: boolean) => {
        api.put("/task/"+ taskDTO.id, {
            userId: userId,
            item: taskDTO.item,
            completed: isDone
        }).then((message: string) => {
            if(message !== undefined){
                store.dispatch(addNotification("The record was successfully edited."));
                setListUpdate(true);
            }
        }).catch((e: any) => {
            store.dispatch(addNotification({message: "The record was not successfully edited.", type:"error"}));
        });
    };

    const handleEdit = (taskDTO: TaskDTO) => {
        setSelectedItem(taskDTO);
        setOpenAddPage(true);
    };

    const handleDelete = (taskDTO: TaskDTO) => {
        setSelectedItem(taskDTO);
        setOpenDeletePage(true);
    };

    const deleteAllTasksByType = (type: string) => {
        setDeleteType(type);
        setOpenAllDeletePage(true);
    }


    return (
        <div style={{width: "95%"}}>
            <MaterialReactTable
                columns={columns}
                data={dataList}
                renderToolbarInternalActions={() => (
                    <Box display={"flex"} alignItems={"center"}>
                        <Button
                            variant="contained"
                            color="primary"
                            startIcon={<Add />}
                            style={{ marginRight: "8px" }}
                            onClick={() => {
                                setOpenAddPage(true);
                            }}
                        >
                            Add
                        </Button>
                        <Button
                            variant="outlined"
                            startIcon={<MoreVert />}
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
                renderBottomToolbarCustomActions={() => (
                    <Box display={"flex"} alignItems={"center"}>
                        <Button
                            variant="contained"
                            color="error"
                            style={{ marginRight: "8px" }}
                            startIcon={<DeleteOutline />}                            
                            onClick={()=>{
                                deleteAllTasksByType("Done")
                            }}
                        >
                            Delete Done Tasks
                        </Button>
                        <Button
                            variant="outlined"
                            color="error"
                            startIcon={<DeleteForever />}
                            onClick={()=>{
                                deleteAllTasksByType("All")
                            }}
                        >
                            Delete All Tasks
                        </Button>
                </Box>
                )}
            />
            <TaskAddPage
                initializer={selectedItem}
                open= {openAddPage}
                handleClose={setOpenAddPage}
                setListUpdate={setListUpdate}
            />
            <TaskDeletePage
                id={selectedItem.id}
                open= {openDeletePage}
                handleClose={setOpenDeletePage}
                setListUpdate={setListUpdate}
            />
             <TaskAllDeletePage
                open= {openAllDeletePage}
                handleClose={setOpenAllDeletePage}
                setListUpdate={setListUpdate}
                type={deleteType}
            />
        </div>
    );
};

export default TaskListPage;