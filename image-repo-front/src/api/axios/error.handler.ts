import { AxiosError, AxiosResponse } from "axios";
import { toast } from "react-toastify";
import { clearToken } from "../services/auth.service";
import { appHealth } from "../services/application.service";

export const handleAxiosError = (error: AxiosError): void => {
    const { message } = error;
    const { status } = error.response as AxiosResponse ?? {};
    if(status === 401)
        onUnauthorized();
    else if(status === 500)
        onServerError();
    else
        toast.warn(message);
}

const onUnauthorized = () => {
    toast.error("Não autenticado");
    clearToken();
}

const onServerError = () => {
    toast.error("Erro no Servidor");
    toast.info("Verificando saúde do Servidor");
    appHealth()
        .then(response => toast.success(response.data))
        .catch(() => toast.error("Servidor Offline, contate nosso suporte"));
}

export const handleError = (error: Error): void => {
    console.log({
        message: "Request Error",
        error: error
    })
}