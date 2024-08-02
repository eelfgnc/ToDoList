import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from "@mui/material";
import api from "../config/AxiosConfig";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { DeleteSharp } from "@mui/icons-material";
import { useSelector } from "react-redux";

interface TaskAddPageProps {
    open: boolean;
    handleClose : (t: boolean) => void;
    setListUpdate: (t: boolean) => void;
    type: string;
}
const TaskAllDeletePage=(props: TaskAddPageProps)=>{
    const userId = useSelector((state:any) => state.user.id);

    const deleteAllTasksByType = () => {
        api.delete("task/all/" + userId + "?type=" + props.type)
        .then(() => {
            store.dispatch(addNotification("All selected task were successfully deleted."));
            props.setListUpdate(true);
            props.handleClose(false);
        })
        .catch((ex: any)=>{
            store.dispatch(addNotification({message:"All selected task were not successfully deleted.", type: "error"}));
        });
    };

    return (
        <Dialog
            open={props.open}
            onClose={props.handleClose}
            fullWidth={true}
            maxWidth={"sm"}
        >
            <DialogTitle id="responsive-dialog-title">
            {"Delete Task?"}
            </DialogTitle>
            <DialogContent>
            <DialogContentText>
                {
                    props.type === "All"?
                    "Are you sure you want to DELETE ALL TASK? This action cannot be undone.":
                    "Are you sure you want to DELETE ALL DONE TASK? This action cannot be undone."
                }
            </DialogContentText>
            </DialogContent>
            <DialogActions>
            <Button
                color="primary"
                variant="outlined"
                onClick={()=>{
                    props.handleClose(false);
                }}
            >
                Cancel
            </Button>
            <Button
                startIcon={<DeleteSharp />} 
                variant="outlined"
                color="error"
                onClick={()=>{
                    deleteAllTasksByType();
                }}
            >
                Delete
            </Button>
            </DialogActions>
      </Dialog>
    );
}
export default TaskAllDeletePage;