import axios, { AxiosResponse } from "axios";
import User from "../../models/user.model";

const apiUrl = process.env.API_URL;

export const createUser = (user: User): Promise<AxiosResponse<void>> => {
    return axios.post(`${apiUrl}/users`, user);
}

export const readUser = (userId: string): Promise<AxiosResponse<User>> => {
    return axios.get(`${apiUrl}/users/${userId}`);
}

export const updateUser = (user: User): Promise<AxiosResponse<void>> => {
    return axios.put(`${apiUrl}/users/${user.id}`, user);
}

export const deleteUser = (userId: string): Promise<AxiosResponse<void>> => {
    return axios.delete(`${apiUrl}/users/${userId}`);
}