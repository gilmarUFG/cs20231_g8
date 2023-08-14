import { styled } from "styled-components"
import { AppContainer, AppGrid, DifferentialCard } from "../../molecules";
import { DifferentialsData, DifferentialsDataType } from "../../../data/Differentials.data";

type AdvantagesSectionProps = {}

const StyledAdvantagesSection = styled.section`
    padding: 64px 0;
    text-align: center;
    > div {
        display: flex;
        flex-direction: column;
        gap: 12px;
        > div {
            margin: 48px 0 64px 0;
        }
    }
`;

const AdvantagesSection: React.FunctionComponent<AdvantagesSectionProps> = (props) => {

    const data: DifferentialsDataType[] = DifferentialsData;

    return (
        <StyledAdvantagesSection>
            <AppContainer>
                <h2>
                    Por quê o PixelPort?
                </h2>
                <h4>
                    Quais são os nossos diferenciais?
                </h4>
                <AppGrid>
                    {
                        data.map((item) => <DifferentialCard key={item.id} data={item} />)
                    }
                </AppGrid>
            </AppContainer>
        </StyledAdvantagesSection>
    );
}

export default AdvantagesSection;