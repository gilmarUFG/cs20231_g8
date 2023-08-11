import { styled } from "styled-components";
import { AppContainer, SignUpForm } from "../components";

type SignUpPageProps = {};

const StyledSignUpPage = styled.section`
    width: 100%;
    padding: 144px 72px 72px 72px;
    background-image: url("./../assets/svgs/meteor-bg.svg");
    > div {
        display: flex;
        justify-content: space-between;
        align-items: center;
        gap: 72px;
        h1 {
            flex: 0.5;
        }
        > div {
            flex: 0.3;
        }
    }
`;

const SignUpPage: React.FunctionComponent<SignUpPageProps> = (props) => {
    return (
        <StyledSignUpPage>
            <AppContainer>
                <h1>
                    Junte-se à Nossa Comunidade Visual: O Seu Espaço para Compartilhar e Brilhar!
                </h1>
                <SignUpForm />
            </AppContainer>
        </StyledSignUpPage>
    );
}

export default SignUpPage;