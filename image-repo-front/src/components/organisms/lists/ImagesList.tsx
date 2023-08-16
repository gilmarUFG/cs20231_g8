import { styled } from "styled-components";
import { AppContainer, AppGrid, ImageCard } from "../../molecules";
import ImagesListFilter from "../filters/ImagesListFilter";
import { useEffect, useState } from "react";
import Media from "../../../models/media.model";
import { readAll } from "../../../api/services/media.service";
import { MediaFilter } from "../../../models/mediafilter.model";
import { useSplashScreen } from "../../../contexts/SplashScreenProvider";
import { toast } from "react-toastify";

type ImagesListProps = {
    $userImages?: boolean;
}

const StyledImagesList = styled.section`
    > div {
        div {
            h6 {
                margin: 30px 0 180px 0;
                text-align: center;
                grid-column: 2;
            }
            > div {
                padding: 48px 0 48px 0;
            }
        }
    }
`;

const ImagesList: React.FunctionComponent<ImagesListProps> = (props) => {

    const [medias, setMedias] = useState<Media[]>([]);

    const [filter, setFilter] = useState<MediaFilter>(new MediaFilter());

    const { start, stop } = useSplashScreen();

    const loadImages = (): void => {
        start();
        readAll(filter).then(
            response => {
                setMedias(response.data);
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

    useEffect(() => {
        loadImages();
    }, []);

    const onApplyFilter = (mediaFilter: MediaFilter): void => {
        setFilter(mediaFilter);
        loadImages();
    }

    return (
        <StyledImagesList>
            <AppContainer>
                <ImagesListFilter onApplyFilter={onApplyFilter}/>
                <AppGrid>
                    {
                        medias.length > 0 ? (
                            medias.map(
                                (media) => <ImageCard key={media.id} media={media} />
                            )
                        )
                        :
                        <h6>Não foram encontradas imagens</h6>
                    }
                </AppGrid>
            </AppContainer>
        </StyledImagesList>
    );
}

export default ImagesList;