import {
    Block,
    Check,
    Close,
    Delete,
    Key,
    KeyOff,
    LockOpen,
    RestoreFromTrash,
} from "@mui/icons-material";
import {
    Box,
    IconButton,
    Tooltip,
    Typography
} from "@mui/material";
import { MaterialReactTable } from "material-react-table";
import { useEffect, useMemo, useState } from "react";
import { UserInformationDTO } from "../dto/UserInformationDTO";
import api from "../config/AxiosConfig";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { useSelector } from "react-redux";

const UserListPage = () => {
    const userId = useSelector((state: any)=> state.user.id);
    const [dataList, setDataList] = useState<UserInformationDTO[]>([]);
    const [refreshData, setRefreshData] = useState<boolean>(true);

    const ListOfAllUser = () => {
        api.get("/users")
        .then((result: UserInformationDTO[]) => {
            setDataList(result);
        })
        .catch(()=>{
            store.dispatch(addNotification({message: "An error occurred while loading the data. Please try again later.", type: "error"}));
        })
    };

    const UserAccountLocked = (isLockedInfo: boolean) => {
        api.put("/users/" + userId + "/lock-account", {
            isLocked: isLockedInfo
        })
        .then(() => {
            setRefreshData(true);
        })
        .catch(()=>{
            store.dispatch(addNotification({message: "Please try again later.", type: "error"}));
        })
    };

    const UserAccountDeleted = (deletedAccount: boolean) => {
        api.put("/users/" + userId + "/delete-account", {
            isDeleted: deletedAccount
        })
        .then(() => {
            setRefreshData(true);
        })
        .catch(()=>{
            store.dispatch(addNotification({message: "Please try again later.", type: "error"}));
        })
    }

    useEffect(() => {
        if(refreshData){
            ListOfAllUser();
            setRefreshData(false);
        }
    },[refreshData]); //eslint-disable-line

    const columns = useMemo(
        () => [
            {
                accessorKey: 'id',
                header: 'Id',
            },
            {
                accessorKey: 'firstName',
                header: 'FirstName',
            },
            {
                accessorKey: 'lastName',
                header: 'LastName',
            },
            {
                accessorKey: 'email',
                header: 'Email',
            },
            {
                accessorKey: 'phone',
                header: 'Phone',                
                columnVisibility:true
                
            },
            {
                accessorKey: 'city',
                header: 'City',
                columnVisibility:true
            },
            {
                accessorKey: 'lockedAccount',
                header: 'Locked Account',
                Cell: ({ row }: any) => (
                    <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                        <IconButton aria-hidden aria-label="locked item" size="medium">
                            <Tooltip title={row.original.lockedAccount ? "Locked" : "Not Locked"}>
                                {row.original.lockedAccount ? (
                                    <Key color="error" fontSize="large"/>
                                ) : (
                                    <KeyOff color="inherit" fontSize="large"/>
                                )}
                            </Tooltip>
                        </IconButton>
                    </div>
                ),
            },
            {
                accessorKey: 'deletedAccount',
                header: 'Deleted Account',
                Cell: ({ row }: any) => (
                    <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                        <IconButton aria-hidden aria-label="locked item" size="medium">
                            <Tooltip title={row.original.deletedAccount ? "Deleted" : "Not Deleted"}>
                                {row.original.deletedAccount ? (
                                    <Check color="error" fontSize="large"/>
                                ) : (
                                    <Close color="inherit" fontSize="large"/>
                                )}
                            </Tooltip>
                        </IconButton>
                    </div>
                ),
            },
            {
                accessorKey: 'createDate',
                header: 'Create Date',
            },
            {
                accessorKey: 'updateDate',
                header: 'Update Date',
            },
            {
                accessorKey: 'lastLoginDate',
                header: 'Last Login Date',
            },
            {
                id: 'actions',
                header: '',
                Cell: ({ row }: any) => (
                    <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                        <IconButton aria-hidden aria-label="locked item" size="medium" onClick={() => {
                            console.log("locked account: " + !row.original.lockedAccount);
                            UserAccountLocked(!row.original.lockedAccount);
                        }}>
                            <Tooltip title={row.original.lockedAccount ? "Set As Unlocked" : "Set As Locked"}>
                                {
                                    row.original.lockedAccount ?
                                    (
                                        <LockOpen color="warning" fontSize="large"/>
                                    ):
                                    (
                                        <Block color="warning" fontSize="large"/>
                                    )
                                }
                            </Tooltip>
                        </IconButton>
                        <IconButton aria-hidden aria-label="delete item" size="medium" onClick={() => {
                            UserAccountDeleted(!row.original.deletedAccount);
                        }}>
                            <Tooltip title={row.original.deletedAccount ? "Set As Not Deleted" : "Set As Deleted"}>
                                {
                                    row.original.deletedAccount ?
                                    (
                                        <RestoreFromTrash color="error" fontSize="large"/>
                                    ):
                                    (
                                        <Delete color="error" fontSize="large"/>
                                    )
                                }
                            </Tooltip>
                        </IconButton>
                    </div>
                ),
            },
        ],
        [dataList]
    );

    return(
        <Box sx={{ width: "94%", margin: "0 auto", mt: 4 }}>
            <Typography variant="h4" gutterBottom>
                User List
            </Typography>
            <MaterialReactTable
                columns={columns}
                data={dataList}
                initialState={{
                    columnVisibility: {
                    city: false,
                    phone: false,
                    },
                }}
            />
        </Box>
    );
};

export default UserListPage;
