import axios, { AxiosResponse } from "axios";
import Media from "../../models/media.model";

const apiUrl = process.env.API_URL;

export const createMedia = (media: Media): Promise<AxiosResponse<void>> => {
    return axios.post(`${apiUrl}/images`, media);
}

export const readMedia = (mediaId: string): Promise<AxiosResponse<Media>> => {
    return axios.get(`${apiUrl}/images/${mediaId}`);
}

export const updateMedia = (media: Media): Promise<AxiosResponse<void>> => {
    return axios.put(`${apiUrl}/images/${media.id}`, media);
}

export const deleteMedia = (mediaId: string): Promise<AxiosResponse<void>> => {
    return axios.delete(`${apiUrl}/images/${mediaId}`);
}