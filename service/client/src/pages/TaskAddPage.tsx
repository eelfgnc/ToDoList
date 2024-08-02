import { Button, Dialog, DialogActions, DialogContent, DialogTitle, InputAdornment, TextField } from "@mui/material";
import React, { useEffect, useState } from "react";
import api from "../config/AxiosConfig";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { useSelector } from "react-redux";
import { Assignment } from "@mui/icons-material";
import { TaskDTO } from "../dto/TaskDTO";

interface TaskAddPageProps {
    initializer: TaskDTO;
    open: boolean;
    handleClose : (t: boolean) => void;
    setListUpdate: (t: boolean) => void;
}
const TaskAddPage=(props: TaskAddPageProps)=>{
    const userId = useSelector((state: any) => state.user.id);
    const [item, setItem] = useState<string>(props.initializer.item);

    const handleSubmit =  async (event: React.FormEvent) => {
        event.preventDefault();
        const taskText = item;
        setItem("");
        if(props.initializer.id === 0){
            api.post("/task", {
                userId: userId,
                item: taskText,
                completed: props.initializer.isDone
            }).then((message: string) => {
                if(message !== undefined){
                    store.dispatch(addNotification("The task was successfully added."));
                    props.setListUpdate(true);
                    props.handleClose(false);
                }
            }).catch((e: any) => {
                store.dispatch(addNotification({message: "The task was not successfully added.", type:"error"}));
            });
        }
        else{
            api.put("/task/"+ props.initializer.id, {
                userId: userId,
                item: taskText,
                completed: props.initializer.isDone
            }).then((message: string) => {
                if(message !== undefined){
                    store.dispatch(addNotification("The task was successfully edited."));
                    props.setListUpdate(true);    
                    props.handleClose(false);
                }
            }).catch((e: any) => {
                store.dispatch(addNotification({message: "The task was not successfully edited.", type:"error"}));
            });
        }
    };

    useEffect(() => {
        setItem(props.initializer.item);
    }, [props.initializer]);

    return (
        <Dialog
            open={props.open}
            onClose={props.handleClose}
            fullWidth={true}
            maxWidth={"sm"}
        >
            <DialogTitle>{props.initializer.id !== 0 ? "Save New Task":"Update Task"}</DialogTitle>
            <DialogContent>
            <form onSubmit={handleSubmit}>
                <TextField
                    autoFocus
                    required
                    margin="dense"
                    id="task"
                    name="task"
                    label="Task"
                    type="text"
                    fullWidth
                    variant="standard"
                    value={item}
                    onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                        setItem(event.target.value);
                    }}
                    InputProps={{
                        startAdornment:(
                            <InputAdornment position="start">
                                <Assignment
                                    color="primary"
                                    fontSize="large"
                                />
                            </InputAdornment>
                        )
                    }}
                />
                <DialogActions>
                    <Button
                    onClick={() => {
                        props.handleClose(false);
                    }}
                    >
                    İptal
                    </Button>
                    <Button type="submit">Ekle</Button>
                </DialogActions>
                </form>
            </DialogContent>
      </Dialog>
    );
}
export default TaskAddPage;