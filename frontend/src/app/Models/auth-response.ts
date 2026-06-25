import { UserDTO } from "./user-dto";

export interface AuthResponse{
    token: string;
    user: UserDTO;
}