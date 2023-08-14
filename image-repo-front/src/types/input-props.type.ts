type InputProps =  {
    name: string;
    label?: string;
    placeholder?: string;
    type?: string;
    required?: boolean;
    disabled?: boolean;
    src?: string;
    readonly?: boolean;
    onChangeValue?: ($event: any) => void;
}

export default InputProps;