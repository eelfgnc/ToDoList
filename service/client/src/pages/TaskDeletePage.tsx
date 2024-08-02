import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from "@mui/material";
import api from "../config/AxiosConfig";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { DeleteSharp } from "@mui/icons-material";

interface TaskAddPageProps {
    id: number;
    open: boolean;
    handleClose : (t: boolean) => void;
    setListUpdate: (t: boolean) => void;
}
const TaskDeletePage=(props: TaskAddPageProps)=>{
    const deleteById = () => {
        api.delete("task/" + props.id)
        .then(() => {
            store.dispatch(addNotification("The task was successfully deleted."));
            props.setListUpdate(true);
            props.handleClose(false);
        })
        .catch(()=>{
            store.dispatch(addNotification({message:"The task was not successfully deleted.", type: "error"}));
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
                Are you sure you want to delete this task?
                This action cannot be undone.
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
                    deleteById();
                }}
            >
                Delete
            </Button>
            </DialogActions>
      </Dialog>
    );
}
export default TaskDeletePage;