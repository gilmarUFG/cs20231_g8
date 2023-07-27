import { AxiosResponse } from "axios";
import { Token } from "../../models/token.model";
import { Credentials } from "../../models/credentials.model";
import api from "../axios/http-common";

const token = "token";

export const login = (credentials: Credentials): Promise<AxiosResponse<Token>> => {
    return api.post<Token>('/users/login', credentials);
}

export const setToken = (token: string): void => {
    localStorage.setItem(token, token);
}

export const getToken = (): string => {
    return localStorage.getItem(token) ?? "";
}

export const clearToken = (): void => {
    localStorage.removeItem(token);
}

export const authenticated = (): boolean => {
    return getToken() !== "";
}