import { styled } from "styled-components";
import { useSplashScreen } from "../../contexts/SplashScreenProvider";

type SplashScreenProps = {};

const StyledSplashScreen = styled.div`
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.8);
    span {
        display: inline-block;
        width: 80px;
        height: 80px;
    }
    span:after {
        content: " ";
        display: block;
        width: 64px;
        height: 64px;
        margin: 8px;
        border-radius: 50%;
        border: 6px solid #fff;
        border-color: #fff transparent #fff transparent;
        animation: lds-dual-ring 1.2s linear infinite;
      }
    @keyframes lds-dual-ring {
        0% {
          transform: rotate(0deg);
        }
        100% {
          transform: rotate(360deg);
        }
    }
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