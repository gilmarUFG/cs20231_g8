type ButtonProps = {
    label: string;
    type?: "button" | "submit" | "reset";
    onClick: () => void | ((parameter: any) => void);
    disabled?: boolean;
    primary?: boolean;
    secondary?: boolean;
    ghost?: boolean;
}

const Button: React.FunctionComponent<ButtonProps> = (props) => {
    const { label, type, onClick, disabled } = props;
    return <button type={type ?? 'button'} disabled={disabled ?? false} onClick={onClick}>{ label }</button>;
}

export default Button;