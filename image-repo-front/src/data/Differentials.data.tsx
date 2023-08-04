import { IconType } from "react-icons";
import { FaRocket } from "react-icons/fa";
import { TfiAlert, TfiAnnouncement } from "react-icons/tfi";

export type DifferentialsDataType = {
    title: String;
    subtitle: String;
    icon: IconType;
}

export const DifferentialsData: DifferentialsDataType[] = [
    {
        title: "Otimizado",
        subtitle: "Tecnologias e estratégias para melhor User Experience",
        icon: FaRocket
    },
    {
        title: "Não é uma Rede Social",
        subtitle: "O foco é publicar sua arte, apenas!",
        icon: TfiAnnouncement
    },
    {
        title: "Conteúdo Seguro",
        subtitle: "Não são permitidas publicações com conteúdo ilegal ou inflamatório",
        icon: TfiAlert
    }
];