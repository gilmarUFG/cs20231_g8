export class EnumDto {

    value: string;
    description: string;

    constructor(
        value: string = '',
        description: string = ''
    ) {
        this.value = value;
        this.description = description;
    }

}