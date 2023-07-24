import { AxiosResponse } from "axios";
import Media from "../../models/media.model";
import api from "../axios/http-common";

export const createMedia = (media: Media): Promise<AxiosResponse<void>> => {
    return api.post<void>('/images', media);
}

export const readMedia = (mediaId: string): Promise<AxiosResponse<Media>> => {
    return api.get<Media>(`/images/${mediaId}`);
}

export const updateMedia = (media: Media): Promise<AxiosResponse<void>> => {
    return api.put<void>(`/images/${media.id}`, media);
}

export const deleteMedia = (mediaId: string): Promise<AxiosResponse<void>> => {
    return api.delete(`/images/${mediaId}`);
}