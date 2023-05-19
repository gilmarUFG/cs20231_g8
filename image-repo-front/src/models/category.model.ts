import BaseEntity from "./base.entity.model";
import Tag from "./tag.model";


export default class Categories extends BaseEntity {

    image: Image;

    tag: Tag;

    
    constructor(id: string, image: Image, tag: Tag , updatedAt: Date, createdAt: Date) {

        super(id, updatedAt, createdAt);


        this.image = image;

        this.tag = tag;
    }

}