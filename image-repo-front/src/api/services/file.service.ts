import axios, { AxiosResponse } from "axios";

const apiUrl = process.env.API_URL;

export const uploadFile = (file: File): Promise<AxiosResponse<void>> => {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return axios.post<void>(`${apiUrl}/files`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}