import { styled } from "styled-components";
import { DifferentialsDataType } from "../../../data/Differentials.data";

type DifferentialCardProps = {
    data: DifferentialsDataType;
};

const StyledDifferentialCard = styled.div`
    width: 240px;
    justify-self: center;
    padding: 24px;
    border: 1px solid #747880;
    border-radius: 4px;
`;

const DifferentialCard: React.FunctionComponent<DifferentialCardProps> = (props) => {
    return (
        <StyledDifferentialCard>
            {props.data.icon({ color: props.data.color })}
            <h5>
                {props.data.title}
            </h5>
            <h6>
                {props.data.subtitle}
            </h6>
        </StyledDifferentialCard>
    )
}

export default DifferentialCard;