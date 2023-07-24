import axios, { AxiosError, AxiosRequestConfig, InternalAxiosRequestConfig } from "axios";
import { authenticated, getToken } from "../services/auth.service";

const api = axios.create({
    baseURL: process.env.API_URL
});

const onRequest = (config: InternalAxiosRequestConfig): InternalAxiosRequestConfig => {
    config.headers = config.headers || {};
    if(authenticated())
        config.headers['Authorization'] = `Bearer ${getToken()}`;
    return config;
}

api.interceptors.request.use(onRequest);

export default api;