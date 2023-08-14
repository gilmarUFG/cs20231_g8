import { IconType } from "react-icons";
import { FaRocket } from "react-icons/fa";
import { TfiAlert, TfiAnnouncement } from "react-icons/tfi";

export type DifferentialsDataType = {
    id: number;
    title: String;
    subtitle: String;
    color: string;
    icon: IconType;
}

export const DifferentialsData: DifferentialsDataType[] = [
    {
        id: 1,
        title: "Otimizado",
        subtitle: "Tecnologias e estratégias para melhor User Experience",
        color: '',
        icon: FaRocket
    },
    {
        id: 2,
        title: "Não é uma Rede Social",
        subtitle: "O foco é publicar sua arte, apenas!",
        color: '',
        icon: TfiAnnouncement
    },
    {
        id: 3,
        title: "Conteúdo Seguro",
        subtitle: "Não são permitidas publicações com conteúdo ilegal ou inflamatório",
        color: '',
        icon: TfiAlert
    }
];