import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios";
import { authenticated, getToken } from "../services/auth.service";
import { handleAxiosError, handleError } from "./error.handler";

const api = axios.create({
    baseURL: process.env.API_URL
});

const onResponse = (response: AxiosResponse): AxiosResponse => {
    return response;
}

const errorHandler = (error: AxiosError | Error): Promise<AxiosError> => {
    axios.isAxiosError(error) ? handleAxiosError(error) : handleError(error);
    return Promise.reject(error);
}

api.interceptors.response.use(onResponse, errorHandler);

export default api;