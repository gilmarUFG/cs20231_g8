import { styled } from "styled-components";
import { AppContainer, ImagesList, ProfileMenu, UserForm } from "../components";
import { useState } from "react";

type ProfilePageProps = {};

const StyledProfilePage = styled.div`
    > div {
        display: flex;
        justify-content: flex-start;
        align-items: center;
    }
`;

const ProfilePage: React.FunctionComponent<ProfilePageProps> = (props) => {

    const [ tab, setTab] = useState<'PROFILE' | 'IMAGES'>('PROFILE');

    return (
        <StyledProfilePage>
            <AppContainer>
                <ProfileMenu onChangeTab={setTab} currentTab={tab} />
                <div>
                    {
                        tab === 'PROFILE' && (
                            <UserForm/>
                        )
                    }
                    {
                        tab === 'IMAGES' && (
                            <ImagesList $userImages/>
                        )
                    }
                </div>
            </AppContainer>
        </StyledProfilePage>
    )
}

export default ProfilePage;