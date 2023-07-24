import axios from "axios";
import { onRequest } from "./request.interceptor";
import { errorHandler, onResponse } from "./response.interceptor";

const api = axios.create({
    baseURL: process.env.API_URL
});

api.interceptors.request.use(onRequest);

api.interceptors.response.use(onResponse, errorHandler);

export default api;