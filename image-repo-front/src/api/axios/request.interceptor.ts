import { InternalAxiosRequestConfig } from "axios";
import { authenticated, getToken } from "../services/auth.service";

export const onRequest = (config: InternalAxiosRequestConfig): InternalAxiosRequestConfig => {
    config.headers = config.headers || {};
    if(authenticated())
        config.headers['Authorization'] = `Bearer ${getToken()}`;
    return config;
}
