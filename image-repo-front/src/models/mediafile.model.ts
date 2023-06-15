import BaseEntity from "./base.entity.model";

export default class MediaFile extends BaseEntity {
    name: string;
    size: number;
    type: string;
    data: number[];

    constructor(
        id: string,
        name: string,
        size: number,
        type: string,
        data: number[],
        updatedAt: Date,
        createdAt: Date
    ) {
        super(id, updatedAt, createdAt);
        this.name = name;
        this.size = size;
        this.type = type;
        this.data = data;
    }
}