import { Field, ErrorMessage } from "formik";
import { styled } from "styled-components";
import InputProps from "../../../types/input-props.type";

const StyledField = styled.input`
    font-size: 12px;
    font-weight: 500;
    padding: 8px;
    border: 1px solid #747880;
    border-radius: 4px;
    transition: 0.2s;
    &:focus {
        outline: 1px solid #747880;
        transition: 0.2s;
    }
`;

const StyledLabel = styled.label`
    font-size: 12px;
    font-weight: 500;
`;

const StyledErrorMessage = styled.span`
    font-size: 10px;
    font-weight: 500;
    opacity: 0.7;
`;

const StyledInput = styled.div`
    display: flex;
    flex-direction: column;
    gap: 4px;
    text-align: left;
    justify-content: center;
    align-items: flex-start;
    * {
        width: 100%;
    }
`;

const Input: React.FunctionComponent<InputProps> = (props) => {

    const { name, label, placeholder, type, required, disabled, readonly, onChangeValue } = props;
 
    return (
        <StyledInput>
            <StyledLabel>
                { label || '' }
                { required ? ' *' : '' }
            </StyledLabel>
            <Field 
                as={StyledField} 
                name={name} 
                type={type} 
                placeholder={placeholder} 
                disabled={disabled} 
                readonly={readonly} 
            />
            <ErrorMessage name={name} component={StyledErrorMessage} />
        </StyledInput>
    );
}

export default Input;