import axios, { InternalAxiosRequestConfig } from "axios";
import { authenticated, getToken } from "https://deploy-pixel-port-production.up.railway.app";

export const onRequest = (config: InternalAxiosRequestConfig): InternalAxiosRequestConfig => {
    config.headers = config.headers || {};
    if(authenticated())
        config.headers['Authorization'] = `Bearer ${getToken()}`;
    return config;
}
