import BaseEntity from "./base.entity.model";

export default class MediaFile extends BaseEntity {
    
    name: string;
    size: number;
    type: string;
    base64: string;

    constructor(
        id: string = '',
        name: string = '',
        size: number = 0,
        type: string = '',
        base64: string = '',
        updatedAt: Date = new Date(),
        createdAt: Date = new Date()
    ) {
        super(id, updatedAt, createdAt);
        this.name = name;
        this.size = size;
        this.type = type;
        this.base64 = base64;
    }
}