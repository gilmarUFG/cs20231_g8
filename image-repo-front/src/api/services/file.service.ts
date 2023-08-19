import { AxiosResponse } from "axios";
import api from "../axios/http-common";
import MediaFile from "../../models/mediafile.model";

export const uploadFile = (file: File): Promise<AxiosResponse<MediaFile>> => {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return api.post<MediaFile>('/files', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}