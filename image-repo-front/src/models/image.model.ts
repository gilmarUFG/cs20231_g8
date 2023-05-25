import BaseEntity from "./base.entity.model";
import User from "./user.model";
import File from "./file.model";
import Categories from "./category.model";

export default class Image extends BaseEntity {
    name: string;
    description: string;
    views: number;
    downloads: number;
    author: User;
    file: File;
    categories: Categories[];

    constructor(
        id: string,
        name: string,
        description: string,
        views: number,
        downloads: number,
        author: User,
        file: File,
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
        this.file = file;
        this.categories = categories;
    }
}