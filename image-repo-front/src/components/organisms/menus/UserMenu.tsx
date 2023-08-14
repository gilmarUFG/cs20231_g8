import { styled } from "styled-components";
import { useAuth } from "../../../contexts/AuthProvider";
import { NavigateFunction, useNavigate } from "react-router-dom";
import { FaUserCircle } from "react-icons/fa";
import { useState } from "react";

type UserMenuProps = {};

const StyledUserMenu = styled.div`
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
    table {
        position: absolute;
        top: 50px;
        left: 50%;
        transform: translateX(-50%);
        z-index: 3;
        tbody {
            background-color: white.
            border: 2px solid #747880;
            border-radius: 4px;
            tr {
                td {
                    button {
                        background-color: inherit;
                    }
                }
            }
        }
    }
`;

const UserMenu: React.FunctionComponent<UserMenuProps> = (props) => {

    const { user, logout } = useAuth();

    const navigate: NavigateFunction = useNavigate();

    const onClickLogout = (): void => {
        navigate("home");
        logout();
    }

    const onClickShowMenu = (): void => {
        setIsShown(!isShown);
    }

    const onClickProfile = (): void => {
        navigate("profile");
    }

    const [isShown, setIsShown] = useState(false);

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
                isShown ??
                    (
                        <table>
                            <tbody>
                                <tr>
                                    <td><button>Meu Perfil</button></td>
                                    <td><button>Minhas Imagens</button></td>
                                    <td><button onClick={onClickLogout}>Sair</button></td>
                                </tr>
                            </tbody>
                        </table>
                    )
            }
        </StyledUserMenu>
    );
}

export default UserMenu;