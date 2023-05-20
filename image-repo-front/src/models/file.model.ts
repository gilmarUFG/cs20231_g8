import BaseEntity from "./base.entity.model";

export default class File extends BaseEntity {
    fileName: string;
    size: number;
    type: string;

    constructor(fileName: string, size: number, type: string, id: string, updatedAt: Date, createdAt: Date) {
        super(id, updatedAt, createdAt);
        this.fileName = fileName;
        this.size = size;
        this.type = type;
    }
}