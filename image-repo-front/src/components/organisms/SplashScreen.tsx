import { styled } from "styled-components";
import { useSplashScreen } from "../../contexts/SplashScreenProvider";

type SplashScreenProps = {};

const StyledSplashScreen = styled.div`

`;

const SplashScreen: React.FunctionComponent<SplashScreenProps> = (props) => {

    const { show } = useSplashScreen();

    return (
        <>
            {
                show && 
                    <StyledSplashScreen>
                        <span></span>
                    </StyledSplashScreen>
            }
        </>
    );
}

export default SplashScreen;