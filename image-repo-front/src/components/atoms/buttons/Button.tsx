import { ReactNode } from "react";

type ButtonProps = {
    children: ReactNode;
    onClick: () => void | ((parameter: any) => void);
    type?: "button" | "submit" | "reset";
    disabled?: boolean;
    primary?: boolean;
    secondary?: boolean;
    ghost?: boolean;
}

const Button: React.FunctionComponent<ButtonProps> = (props) => {
    const { type, onClick, disabled } = props;
    return <button type={type ?? 'button'} disabled={disabled ?? false} onClick={onClick}>{ props.children }</button>;
}

export default Button;