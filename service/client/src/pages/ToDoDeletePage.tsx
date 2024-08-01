import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField } from "@mui/material";
import api from "../config/AxiosConfig";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { ToDoItemDTO } from "../dto/ToDoItemDTO";

interface ToDoAddPageProps {
    initializer: ToDoItemDTO;
    open: boolean;
    handleClose : (t: boolean) => void;
    setListUpdate: (t: boolean) => void;
}
const ToDoDeletePage=(props: ToDoAddPageProps)=>{
    const handleSubmit = () => {
        api.delete("todo/" + props.initializer.id)
        .then(() => {
            store.dispatch(addNotification("Başarılı bir şekilde silindi."));
            props.setListUpdate(true); 
            props.handleClose(false);
        })
        .catch((ex: any)=>{
            store.dispatch(addNotification("Silme işlemi sırasında bir hata ile karşılaşıldı."));
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
            {"Delete Todo?"}
            </DialogTitle>
            <DialogContent>
            <DialogContentText>
                Are you sure you want to delete this item?
                This action cannot be undone.
            </DialogContentText>
            </DialogContent>
            <DialogActions>
            <Button autoFocus onClick={()=>{
                props.handleClose(false);
            }}>
                Cancel
            </Button>
            <Button onClick={()=>{
                handleSubmit();
            }} autoFocus>
                Delete
            </Button>
            </DialogActions>
      </Dialog>
    );
}
export default ToDoDeletePage;