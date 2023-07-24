import axios, { AxiosResponse } from "axios";
import Tag from "../../models/tag.model";

const apiUrl = process.env.API_URL;

export const createTag = (tag: string): Promise<AxiosResponse<Tag>> => {
    return axios.post<Tag>(`${apiUrl}/tags/${tag}`);
}