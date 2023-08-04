import { NavLink } from "react-router-dom";
import { styled } from "styled-components";

type NavigationProps = {
    flexColumn?: boolean;
};

const StyledNavigation = styled.nav< {flexColumn?: boolean} >`ul {
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
    ${props => 
        props.flexColumn &&
            `
                flex-direction: column;
                align-items: flex-start;
            `
    }
}
`;

const Navigation: React.FunctionComponent<NavigationProps> = (props) => {
    return (
        <StyledNavigation flexColumn={props.flexColumn}>
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
        </StyledNavigation>
    );
};

export default Navigation;