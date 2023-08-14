import { styled } from "styled-components";
import InputProps from "../../../types/input-props.type";
import { FaUserCircle } from "react-icons/fa";

const StyledFileInput = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 12px;
    img, svg {
        width: 48px;
        height: 48px;
    }
    img {
        border: 1px solid #747880;
        border-radius: 24px;
    }
    label {
        min-width: 96px;
        font-size: 12px;
        font-weight: 500;
        color: #2491ff;
        background-color: white;
        border: 2px solid #2491ff;
        border-radius: 8px;
        padding: 8px 20px;
        transition: 0.2s;
        cursor: pointer;
        &:hover, &:focus {
            color: white;
            background-color: #2491ff;
            transition: 0.2s;
        }
        input {
            display: none;
        }
    }
`;

const FileInput: React.FunctionComponent<InputProps> = (props) => {

    const { name, placeholder, src, onChangeValue } = props;

    return (
        <StyledFileInput>
            {
                src ? 
                    <img src={'data:image/png;base64,' + src}/>
                :
                    <FaUserCircle color="#2491ff" />
            }
            <label htmlFor={name}>
                <input id={name} name={name} onChange={onChangeValue} type="file" />
                { placeholder }
            </label>
        </StyledFileInput>
    );
}

export default FileInput;