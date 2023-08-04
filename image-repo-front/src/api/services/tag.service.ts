import { AxiosResponse } from "axios";
import Tag from "../../models/tag.model";
import api from "../axios/http-common";

export const createTag = (tag: string): Promise<AxiosResponse<Tag>> => {
    return api.post<Tag>(`/tags/${tag}`);
}