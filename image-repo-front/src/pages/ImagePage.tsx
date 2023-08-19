import { styled } from "styled-components";
import { AppContainer } from "../components";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import Media from "../models/media.model";
import { readMedia } from "../api/services/media.service";
import { useSplashScreen } from "../contexts/SplashScreenProvider";
import { toast } from "react-toastify";

type ImagePageProps = {
    $userImage?: boolean;
}

const StyledImagePage = styled.div`
    margin: 128px 0 64px 0;
    > div {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
`;

const ImagePage: React.FunctionComponent<ImagePageProps> = (props) => {

    const { start, stop } = useSplashScreen();

    const [media, setMedia] = useState<Media>(new Media());

    const { mediaId } = useParams();

    const loadMedia = (): void => {
        start();
        if(mediaId) {
            readMedia(mediaId).then(
                response => {
                    setMedia(response.data);
                    stop();
                }
            )
            .catch(
                error => {
                    toast.error(error.message);
                    stop();
                }
            )
        }
    }
    
    useEffect(() => {
        loadMedia();
    }, []);

    return (
        <StyledImagePage>
            <AppContainer>
                <img src={'data:image/png;base64,' + media.file.base64} />
                <h3>{media.name}</h3>
                <h6>{media.description}</h6>
            </AppContainer>
        </StyledImagePage>
    )
}

export default ImagePage;