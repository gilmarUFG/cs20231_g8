import { styled } from "styled-components";
import { AppContainer, ImagesList } from "../components";

type ImagesPageProps = {};

const StyledImagesPage = styled.div`
    margin-top: 100px;
    > div {
        display: flex;
        flex-direction: column;
        gap: 4px;
        > h6 {
            margin-bottom: 16px;
        }
    }
`;

const ImagesPage: React.FunctionComponent<ImagesPageProps> = (props) => {
    return (
        <StyledImagesPage>
            <AppContainer>
                <h1>Imagens</h1>
                <h6>Explore as diversas imagens cadastradas em nossa plataforma.</h6>
                <ImagesList/>
            </AppContainer>
        </StyledImagesPage>
    )
}

export default ImagesPage;