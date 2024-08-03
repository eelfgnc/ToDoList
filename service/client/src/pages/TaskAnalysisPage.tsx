import React, { useState } from "react";
import { Box, Button, Typography } from "@mui/material";
import { MaterialReactTable } from "material-react-table";
import api from "../config/AxiosConfig";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import dayjs, { Dayjs } from 'dayjs';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';


interface TaskAnalysisData {
  date: string;
  addedTasks: number;
  updatedTasks: number;
  totalTasks: number;
  doneTasks: number;
}

const TaskAnalysisPage = () => {
  const [startDate, setStartDate] = React.useState<Dayjs | null>(dayjs('2022-04-17'));
  const [endDate, setEndDate] = React.useState<Dayjs | null>(dayjs('2022-04-17'));
  const [analysisData, setAnalysisData] = useState<TaskAnalysisData[]>([]);

  const handleAnalyze = () => {
    if (!startDate || !endDate) {
      store.dispatch(
        addNotification({
          message: "Please select both start and end dates.",
          type: "error",
        })
      );
      return;
    }

    // Example API call to get analysis data
    api
      .get("/task/analysis", {
        params: {
          startDate: startDate.format("YYYY-MM-DD"),
          endDate: endDate.format("YYYY-MM-DD"),
        },
      })
      .then((response) => {
        setAnalysisData(response.data);
      })
      .catch(() => {
        store.dispatch(
          addNotification({
            message: "An error occurred while loading the data. Please try again later.",
            type: "error",
          })
        );
      });
  };

  const columns = [
    {
      accessorKey: "date",
      header: "Date",
    },
    {
      accessorKey: "addedTasks",
      header: "Added Tasks",
    },
    {
      accessorKey: "updatedTasks",
      header: "Updated Tasks",
    },
    {
      accessorKey: "totalTasks",
      header: "Total Tasks",
    },
    {
      accessorKey: "doneTasks",
      header: "Done Tasks",
    },
  ];

  return (
    <Box sx={{ width: "94%", margin: "0 auto", mt: 4 }}>
      <Typography variant="h4" gutterBottom>
        Task Analysis
      </Typography>

      <Box sx={{ display: "flex", alignItems: "center", gap: 2, mb: 4 }}>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DemoContainer components={['DatePicker', 'DatePicker']}>
                <DatePicker
                    label="Controlled picker"
                    value={startDate}
                    onChange={(newValue) => setStartDate(newValue)}
                 />
                <DatePicker
                    label="Controlled picker"
                    value={endDate}
                    onChange={(newValue) => setEndDate(newValue)}
                />
            </DemoContainer>
        </LocalizationProvider>
        <Button variant="contained" onClick={handleAnalyze}>
          Analyze
        </Button>
      </Box>

      <MaterialReactTable columns={columns} data={analysisData} />
    </Box>
  );
};

export default TaskAnalysisPage;
