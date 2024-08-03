import { MaterialReactTable } from "material-react-table";
import { useEffect, useMemo, useState } from "react";
import api from "../config/AxiosConfig";
import { useSelector } from "react-redux";
import { Box, Tooltip, Typography } from "@mui/material";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { TaskInformationDTO } from "../dto/TaskInformationDTO";
import { Check, Close } from "@mui/icons-material";

const HistoryTaskListPage = () => {
    const userId = useSelector((state: any) => state.user.id);
    const [dataList, setDataList] = useState<TaskInformationDTO[]>([]);


	const GetHistoryOfTaskList = async () => {
		await api.get("/task/user/" +  userId + "/history").then((taskDTO: TaskInformationDTO[]) => {
			setDataList(taskDTO);
		}).catch(() => {
            store.dispatch(addNotification({message: "An error occurred while loading the data. Please try again later.", type: "error"}));
        });
	};

    useEffect(() => {
		GetHistoryOfTaskList();
	}, []); // eslint-disable-line

    const columns = useMemo(
        () => [
            {
              accessorKey: 'id',
              header: 'ID',
            },
            {
                accessorKey: 'item',
                header: 'Item',
            },
            {
                accessorKey: 'isDone',
                header: 'Done?',
                Cell: ({ row }: any) => (
                    <div>
                      {row.original.isDone ? (
                        <Check color="primary" fontSize="large" />
                      ) : (
                        <Close color="inherit" fontSize="large" />
                      )}
                    </div>
                  ),
            },
            {
                accessorKey: 'isDelete',
                header: 'Delete?',
                Cell: ({ row }: any) => (
                    <div>
                        {row.original.isDelete ? (
                        <Check color="primary" fontSize="large" />
                        ) : (
                        <Close color="inherit" fontSize="large" />
                        )}
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
            }
        ],
        [dataList]
    );


    return (
      <Box sx={{ width: "94%", margin: "0 auto", mt: 4 }}>
        <Typography variant="h4" gutterBottom>
          History Of Task
        </Typography>
        <MaterialReactTable
          columns={columns}
          data={dataList}
        />        
      </Box>
    );
};

export default HistoryTaskListPage;