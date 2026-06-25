export interface RegisterRequest{
    name: string;
    surname: string;
    email: string;
    password: string;
    address?: string;
    city?: string;
    cp?: string;
    country?: string;
    phoneNumber?: string;
}