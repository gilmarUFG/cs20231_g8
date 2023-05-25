import BaseEntity from "./base.entity.model";
import Categories from "./category.model";

export default class Tag extends BaseEntity {
    name: string;
    color: string;
    categories: Categories[];

    constructor(
        id: string,
        name: string,
        color: string,
        categories: Categories[],
        updatedAt: Date,
        createdAt: Date
    ) {
        super(id, updatedAt, createdAt);
        this.name = name;
        this.color = color;
        this.categories = categories;
    }
}