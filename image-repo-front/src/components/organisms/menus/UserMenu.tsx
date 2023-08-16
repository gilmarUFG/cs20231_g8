import { styled } from "styled-components";
import { useAuth } from "../../../contexts/AuthProvider";
import { NavigateFunction, useNavigate } from "react-router-dom";
import { FaUserCircle } from "react-icons/fa";
import { useState } from "react";

type UserMenuProps = {};

const StyledUserMenu = styled.nav`
    position: relative;
    button {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 12px;
        background-color: inherit;
        border: none;
        cursor: pointer;
        &:hover {
            background-color: inherit;
            outline: none;
        }
        img, svg {
            width: 32px;
            height: 32px;
        }
        img {
            border: 1px solid #747880;
            border-radius: 16px;
        }
        p {
            font-size: 12px;
            font-weight: 500;
        }
    }
    ul {
        min-width: 180px;
        padding: 8px;
        display: flex;
        flex-direction: column;
        gap: 4px;
        justify-content: center;
        align-items: center;
        position: absolute;
        top: 50px;
        left: 50%;
        transform: translateX(-50%);
        background-color: white;
        background-color: white.
        border: 2px solid #747880;
        border-radius: 4px;
        z-index: 3;
        list-style:none;
        li {
            button {
                font-size: 14px;
                font-weight: 400;
                background-color: inherit;
                &:hover {
                    font-weight: 500;
                }
            }
        }
    }
`;

const UserMenu: React.FunctionComponent<UserMenuProps> = (props) => {

    const { user, logout } = useAuth();

    const navigate: NavigateFunction = useNavigate();

    const [isShown, setIsShown] = useState(false);

    const onClickShowMenu = (): void => {
        setIsShown(!isShown);
    }

    const onClickProfile = (): void => {
        navigate("/profile");
    }

    const onClickLogout = (): void => {
        logout();
        navigate("");
    }

    return (
        <StyledUserMenu>
            <button onClick={onClickShowMenu}>
                {
                    user?.profilePicture?.base64
                    ?
                        <img src={user?.profilePicture?.base64} />
                    :
                        <FaUserCircle color="#2491ff" />
                }
                <p>{user.name}</p>
            </button>
            {
                isShown && 
                    (
                        <ul onMouseLeave={() => setIsShown(false)}>
                            <li><button onClick={onClickProfile}>Meu Perfil</button></li>
                            <li><button onClick={onClickLogout}>Sair</button></li>
                        </ul>
                    )
            }
        </StyledUserMenu>
    );
}

export default UserMenu;