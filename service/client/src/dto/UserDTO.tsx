export interface UserDTO {
    firstName: string;
    lastName: string;
    phone: string;
    city: string;
    email: string;
    password: string;
}

export const InitializeUserDTO = {
    firstName:"",
    lastName:"",
    phone:"",
    city:"",
    email:"",
    password:""
} as UserDTO;