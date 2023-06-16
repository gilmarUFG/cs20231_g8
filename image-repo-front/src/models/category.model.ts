import BaseEntity from "./base.entity.model";
import Media from "./media.model";
import Tag from "./tag.model";

export default class Categories extends BaseEntity {
    image: Media;
    tag: Tag;

    constructor(id: string, image: Media, tag: Tag, updatedAt: Date, createdAt: Date) {
        super(id, updatedAt, createdAt);
        this.image = image;
        this.tag = tag;
    }
}