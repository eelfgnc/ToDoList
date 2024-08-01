import { Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from "@mui/material";
import React, { useEffect, useState } from "react";
import api from "../config/AxiosConfig";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { useSelector } from "react-redux";
import { ToDoItemDTO } from "../dto/ToDoItemDTO";
import { format } from "date-fns";

interface ToDoAddPageProps {
    initializer: ToDoItemDTO;
    open: boolean;
    handleClose : (t: boolean) => void;
    setListUpdate: (t: boolean) => void;
}
const ToDoAddPage=(props: ToDoAddPageProps)=>{
    const userId = useSelector((state: any) => state.user.id);
    const [item, setItem] = useState<string>(props.initializer.item);

    const handleSubmit =  async (event: React.FormEvent) => {
        event.preventDefault();
        const todoText = item;
        setItem("");
        if(props.initializer.id === 0){
            api.post("/todo", {
                userId: userId,
                item: todoText,
                dueTime: format(new Date(),'dd-MM-yyyy HH:mm:ss')
            }).then((message: string) => {
                if(message !== undefined){
                    store.dispatch(addNotification("Başarılı bir şekilde kaydedildi."));
                    props.setListUpdate(true);                
                    props.handleClose(false);
                }
            }).catch((e: any) => {
                store.dispatch(addNotification("Kayıt ekleme işlemi başarısız."));
            });
        }
        else{
            api.put("/todo/"+ props.initializer.id, {
                userId: userId,
                item: todoText,
                dueTime: format(new Date(), 'dd-MM-yyyy HH:mm:ss')
            }).then((message: string) => {
                if(message !== undefined){
                    store.dispatch(addNotification("Başarılı bir şekilde güncellendi."));
                    props.setListUpdate(true);                
                    props.handleClose(false);
                }
            }).catch((e: any) => {
                store.dispatch(addNotification("Güncelleme işlemi başarısız."));
            });
        }

      };
;
    
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
            <DialogTitle>{props.initializer.id !== 0 ? "Save Todo":"Update Todo"}</DialogTitle>
            <DialogContent>
            <form onSubmit={handleSubmit}>
                <TextField
                    autoFocus
                    required
                    margin="dense"
                    id="todo"
                    name="todo"
                    label="Todo"
                    type="text"
                    fullWidth
                    variant="standard"
                    value={item}
                    onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                        setItem(event.target.value);                    
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
export default ToDoAddPage;