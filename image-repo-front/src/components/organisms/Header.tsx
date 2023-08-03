import { NavLink, NavigateFunction, useNavigate } from "react-router-dom";
import { styled } from "styled-components";
import { FlatButton } from "../atoms";

type HeaderProps = {};

const StyledHeader = styled.header`
    display: flex;
    align-items: center;
    gap: 64px;
    padding: 24px 100px;
    background-color: #e1effc;
    nav {
        flex: 1;
        display: flex;
        justify-content: space-between;
        ul {
            display: flex;
            align-items: center;
            gap: 24px;
            list-style-type: none;
            li {
                a {
                    font-size: 12px;
                    color: black;
                    text-decoration: none;
                    transition: 0.2s;
                    &:hover, &:focus, &.active {
                        font-weight: 500;
                        color: black;
                        transition: 0.2s;
                    }
                }
            }
        }
    }
`;

const Header: React.FunctionComponent<HeaderProps> = (props) => {

    const navigate: NavigateFunction = useNavigate();

    return (
        <StyledHeader>
            <h3>Pixel Port</h3>
            <nav>
                <ul>
                    <li>
                        <NavLink to="/">Home</NavLink>
                    </li>
                    <li>
                        <NavLink to="/images">Imagens</NavLink>
                    </li>
                    <li>
                        <NavLink to="/about">Sobre</NavLink>
                    </li>
                </ul>
                <ul>
                    <FlatButton type="button" secondary onClick={() => navigate("signup")}>
                        <NavLink to="/signup">Sign Up</NavLink>
                    </FlatButton>
                    <FlatButton type="button" onClick={() => navigate("login")}>
                        <NavLink to="/login">Login</NavLink>
                    </FlatButton>
                </ul>
            </nav>
        </StyledHeader>
    );
};

export default Header;