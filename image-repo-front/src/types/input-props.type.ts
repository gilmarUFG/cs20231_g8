type InputProps =  {
    name: string;
    label?: string;
    placeholder?: string;
    type?: string;
    required?: boolean;
    disabled?: boolean;
    readonly?: boolean;
    onChangeValue?: ($event: any) => void;
}

export default InputProps;