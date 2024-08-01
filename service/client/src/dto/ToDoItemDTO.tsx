export interface ToDoItemDTO {
    id: number;
    item: string;
    isDone: boolean;
};

export const ToDoItemInitialize: ToDoItemDTO =  {
    id: 0,
    item:"",
    isDone:false
}