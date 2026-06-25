export interface UserDTO{
    id: number;
    name: string;
    surname: string;
    email: string;
    password?: string;
    role: 'CLIENTE' | 'ADMIN';
    address?: string;
    city?: string;
    cp?: string;
    country?: string;
    phoneNumber?: string;
}

