import BaseEntity from "./base.entity.model";
import Categories from "./category.model";

export default class Tag extends BaseEntity {
    
    tag: string;
    tagBackground: string;
    tagTextColor: string;

    constructor(
        id: string = '',
        tag: string = '',
        tagBackground: string = '',
        tagTextColor: string = '',
        updatedAt: Date = new Date(),
        createdAt: Date = new Date()
    ) {
        super(id, updatedAt, createdAt);
        this.tag = tag;
        this.tagBackground = tagBackground;
        this.tagTextColor = tagTextColor;
    }
}