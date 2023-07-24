import { AxiosResponse } from "axios";
import api from "../axios/http-common";

export const uploadFile = (file: File): Promise<AxiosResponse<void>> => {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return api.post<void>('/files', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}