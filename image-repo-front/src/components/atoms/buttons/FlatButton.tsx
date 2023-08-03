import styled from "styled-components";

const FlatButton = styled.button<{ secondary?: boolean; }>`
    min-width: 96px;
    padding: 8px 20px;
    background-color: #2491ff;
    border: 2px solid #2491ff;
    border-radius: 8px;
    cursor: pointer;
    transition: 0.2s;
    * {
        color: white;
        font-size: 12px;
        font-weight: 500;
        text-decoration: none;
        transition: 0.2s;
    }
    &:hover {
        background-color: white;
        border: 2px solid #2491ff;
        transition: 0.2s;
        * {
            color: #2491ff;
            transition: 0.2s;
        }
    }
    ${ props =>
        props.secondary &&
            `
                background-color: white;
                border: 2px solid #2491ff;
                * {
                    color: #2491ff;
                }
                &:hover {
                    background-color: #2491ff;
                    border: 2px solid #2491ff;
                    * {
                        color: white;
                    }
                }
            `
    }
`;

export default FlatButton;