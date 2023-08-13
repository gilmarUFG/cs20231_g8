import { styled } from "styled-components";
import { AppContainer, LoginForm } from "../components";

type LoginPageProps = {};

const StyledLoginPage = styled.section`
    width: 100%;
    padding: 144px 72px 72px 72px;
    > div {
        display: flex;
        justify-content: center;
        align-items: center;
    }
`;

const LoginPage: React.FunctionComponent<LoginPageProps> = (props) => {
    return (
        <StyledLoginPage>
            <AppContainer>
                <LoginForm />
            </AppContainer>
        </StyledLoginPage>
    );
}

export default LoginPage;