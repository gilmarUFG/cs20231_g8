export default class BaseEntity {
    id: string;
    updatedAt: Date;
    createdAt: Date;

    constructor(id: string, updatedAt: Date, createdAt: Date) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}