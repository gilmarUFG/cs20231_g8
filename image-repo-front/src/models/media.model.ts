import BaseEntity from "./base.entity.model";
import User from "./user.model";
import MediaFile from "./mediafile.model";
import Categories from "./category.model";

export default class Media extends BaseEntity {
    name: string;
    description: string;
    views: number;
    downloads: number;
    author: User;
    mediaFile: MediaFile;
    categories: Categories[];

    constructor(
        id: string,
        name: string,
        description: string,
        views: number,
        downloads: number,
        author: User,
        mediaFile: MediaFile,
        categories: Categories[],
        updatedAt: Date,
        createdAt: Date
    ) {
        super(id, updatedAt, createdAt);
        this.name = name;
        this.description = description;
        this.views = views;
        this.downloads = downloads;
        this.author = author;
        this.mediaFile = mediaFile;
        this.categories = categories;
    }
}