import { styled } from "styled-components";

type ProfileMenuProps = {
    onChangeTab: (tab: 'PROFILE' | 'IMAGES') => void;
    currentTab: 'PROFILE' | 'IMAGES';
};

const StyledProfileMenu = styled.nav`

`;

const ProfileMenu: React.FunctionComponent<ProfileMenuProps> = (props) => {

    const { onChangeTab } = props;

    return (
        <StyledProfileMenu>
            <ul>
                <li><button onClick={() => onChangeTab("PROFILE")}>Meu Perfil</button></li>
                <li><button onClick={() => onChangeTab("IMAGES")}>Minhas Imagens</button></li>
            </ul>
        </StyledProfileMenu>
    );
}

export default ProfileMenu;