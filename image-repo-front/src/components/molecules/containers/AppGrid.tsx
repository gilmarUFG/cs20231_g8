import { styled } from "styled-components";

type AppGridProps = {
    columns?: number;
}

const AppGrid = styled.div<AppGridProps>`
    display: grid;
    grid-template-columns: repeat(${props => props.columns ? +props.columns : '3'}, 1fr);
    gap: 24px;
    & .big-item {
        grid-column: 1/-1;
    }
    & .md-item {
        grid-column: span 2;
    }
    & .small-item {
        grid-column: span 1;
    }
`;

export default AppGrid;