import { AxiosResponse } from "axios"
import { EnumDto } from "../../models/enum.model";
import api from "../axios/http-common";

export const appHealth = (): Promise<AxiosResponse<string>> => {
    return api.get<string>('/app');
}

export const loadEnum = (enumType: string): Promise<AxiosResponse<EnumDto>> => {
    return api.get<EnumDto>(`/enum/${enumType}`);
}