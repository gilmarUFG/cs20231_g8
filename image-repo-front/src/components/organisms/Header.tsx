import { NavLink, NavigateFunction, useNavigate } from "react-router-dom";
import { styled } from "styled-components";
import { FlatButton } from "../atoms";
import Navigation from "./Navigation";

type HeaderProps = {};

const StyledHeader = styled.header`
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    background-color: none;
    > div {
        max-width: 1280px;
        margin: 0 auto;
        padding: 24px 100px;
        display: flex;
        align-items: center;
        gap: 64px;
        div {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: space-between;
            > ul {
                display: flex;
                gap: 12px;
            }
        }
    }
`;

const Header: React.FunctionComponent<HeaderProps> = (props) => {

    const navigate: NavigateFunction = useNavigate();

    return (
        <StyledHeader>
            <div>
                <h3>Pixel Port</h3>
                <div>
                    <Navigation />
                    <ul>
                        <FlatButton type="button" secondary onClick={() => navigate("signup")}>
                            <NavLink to="/signup">Sign Up</NavLink>
                        </FlatButton>
                        <FlatButton type="button" onClick={() => navigate("login")}>
                            <NavLink to="/login">Login</NavLink>
                        </FlatButton>
                    </ul>
                </div>
            </div>
        </StyledHeader>
    );
};

export default Header;