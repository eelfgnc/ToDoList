export interface TaskDTO {
    id: number;
    item: string;
    isDone: boolean;
};

export const TaskInitialize: TaskDTO =  {
    id: 0,
    item:"",
    isDone:false
}