export interface UserInformationDTO {
    id:number;
    firstName:string;
    lastName:string;
    email:string;
    city:string;
    phone:string;
    lockedAccount:boolean;
    deletedAccount:boolean;
    createDate:Date;
    updateDate:Date;
    lastLoginDate:Date;
}