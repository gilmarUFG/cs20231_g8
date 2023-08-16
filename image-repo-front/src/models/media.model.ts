import BaseEntity from "./base.entity.model";
import MediaFile from "./mediafile.model";
import Tag from "./tag.model";
import { Report } from "./report.model";

export default class Media extends BaseEntity {
    
    name: string;   
    description: string;
    views: number;
    downloads: number;
    authorName: string;
    authorId: string;
    tagsId: string[];
    tags: Tag[];
    file: MediaFile;
    fileId: string;
    reports: Report[];

    constructor(
        id: string = '',
        name: string = '',
        description: string = '',
        views: number = 0,
        downloads: number = 0,
        authorName: string = '',
        authorId: string = '',
        tagsId: string[] = [],
        tags: Tag[] = [],
        file: MediaFile = new MediaFile(),
        fileId: string = '',
        reports: Report[] = [],
        updatedAt: Date = new Date(),
        createdAt: Date = new Date()
    ) {
        super(id, updatedAt, createdAt);
        this.name = name;
        this.description = description;
        this.views = views;
        this.downloads = downloads;
        this.authorName = authorName;
        this.authorId = authorId;
        this.tagsId = tagsId;
        this.tags = tags;
        this.file = file;
        this.fileId = fileId;
        this.reports = reports;
    }
}