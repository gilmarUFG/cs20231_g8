import axios, { AxiosError, AxiosResponse } from "axios";
import { handleAxiosError, handleError } from "./error.handler";

export const onResponse = (response: AxiosResponse): AxiosResponse => {
    return response;
}

export const errorHandler = (error: AxiosError | Error): Promise<AxiosError> => {
    axios.isAxiosError(error) ? handleAxiosError(error) : handleError(error);
    return Promise.reject(error);
}