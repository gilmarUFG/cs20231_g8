import { styled } from "styled-components";
import Media from "../../../models/media.model"
import { useNavigate } from "react-router-dom";

type ImageCardProps = {
    media: Media;
}

const StyledImageCard = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    border-radius: 1px solid #747880;
    border-radius: 8px;
    gap: 8px;
    img {
        width: 100%;
        max-height: 240px;
        border-radius: 8px;
        cursor: pointer;
    }
    span {
        font-size: 12px;
        font-weight: 500;
    }
`;

const ImageCard: React.FunctionComponent<ImageCardProps> = (props) => {

    const navigate = useNavigate();

    const { media } = props;

    return (
        <StyledImageCard>
            <img src={'data:image/png;base64,' + media.file.base64} onClick={() => navigate(`/images/${media.id}`)}/>
            <span>{ media.name }</span>
        </StyledImageCard>
    );
}

export default ImageCard;