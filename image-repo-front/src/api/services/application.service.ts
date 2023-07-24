import axios, { AxiosResponse } from "axios"
import { EnumDto } from "../../models/enum.model";

const apiUrl = process.env.API_URL;

export const appHealth = (): Promise<AxiosResponse<string>> => {
    return axios.get<string>(`${apiUrl}/app`);
}

export const loadEnum = (enumType: string): Promise<AxiosResponse<EnumDto>> => {
    return axios.get<EnumDto>(`${apiUrl}/enum/${enumType}`);
}