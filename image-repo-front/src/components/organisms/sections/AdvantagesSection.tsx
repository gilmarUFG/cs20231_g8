import { styled } from "styled-components"
import { AppContainer } from "../../molecules";

type AdvantagesSectionProps = {}

const StyledAdvantagesSection = styled.section`

`;

const AdvantagesSection: React.FunctionComponent<AdvantagesSectionProps> = (props) => {
    return (
        <StyledAdvantagesSection>
            <AppContainer>
                <h2>
                    Por quê o PixelPort?
                </h2>
                <h5>
                    Quais são os nossos diferenciais?
                </h5>
            </AppContainer>
        </StyledAdvantagesSection>
    );
}

export default AdvantagesSection;