import { AxiosResponse } from "axios";
import User from "../../models/user.model";
import api from "../axios/http-common";

export const createUser = (user: User): Promise<AxiosResponse<void>> => {
    return api.post<void>('/users', user);
}

export const readUser = (userId: string): Promise<AxiosResponse<User>> => {
    return api.get<User>(`/users/${userId}`);
}

export const updateUser = (user: User): Promise<AxiosResponse<void>> => {
    return api.put<void>(`/users/${user.id}`, user);
}

export const deleteUser = (userId: string): Promise<AxiosResponse<void>> => {
    return api.delete<void>(`/users/${userId}`);
}