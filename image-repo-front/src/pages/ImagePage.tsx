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
                
            </AppContainer>
        </StyledImagePage>
    )
}

export default ImagePage;