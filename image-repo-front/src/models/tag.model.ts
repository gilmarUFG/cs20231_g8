import BaseEntity from "./base.entity.model";

export default class Tag extends BaseEntity {

    name: string;

    color: string;

    categories: Categories[];

    constructor(name: string, color: string, categories: Categories, id: string, updatedAt: Date, createdAt: Date) {

        super(id, updatedAt, createdAt);


        this.name = name;

        this.color = color;

        this.categories = categories;

    }

}