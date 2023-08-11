import { styled } from "styled-components";
import { AppContainer } from "../../molecules";
import { FlatButton } from "../../atoms";
import { NavLink, useNavigate } from "react-router-dom";

type CalloutSectionProps = {};

const StyledCalloutSection = styled.section`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-center: center;
    text-align: center;
    gap: 12px;
    margin-bottom: 64px;
    padding: 24px;
    color: white;
    background-color: #1e72fa;
    border-radius: 8px;
    div {
        display: flex;
        justify-content: center;
        align-center: center;
        gap: 24px;
        button {
            width: 280px;
        }
    }
`;

const CalloutSection: React.FunctionComponent<CalloutSectionProps> = (props) => {

    const navigate = useNavigate();

    return (
        <AppContainer>
            <StyledCalloutSection>
                <h1>
                    Pixel Port
                </h1>
                <h2>
                    O Poder da Imagem: Inspire, Conecte, Compartilhe!
                </h2>
                <h6>
                    Conheça mais sobre a plataforma
                </h6>
                <div>
                    <FlatButton type="button" $secondary onClick={() => navigate("images")}>
                        <p>
                            Ver imagens
                        </p>
                    </FlatButton>
                    <FlatButton type="button" onClick={() => navigate("about")}>
                        <NavLink to="/about">Mais sobre o Pixel Port</NavLink>
                    </FlatButton>
                </div>
            </StyledCalloutSection>
        </AppContainer>
    );
}

export default CalloutSection;