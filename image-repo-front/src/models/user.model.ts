import MediaFile from "./mediafile.model";
import BaseEntity from "./base.entity.model";
import { Report } from "./report.model";

export default class User extends BaseEntity {

    login: string;
    password: string;
    name: string;
    profilePictureId: string;
    profilePicture: MediaFile;
    reports: Report[];

    constructor(
        id: string = '',
        login: string = '',
        password: string = '',
        name: string = '',
        profilePictureId: string = '',
        profilePicture: MediaFile = new MediaFile(),
        reports: Report[] = [],
        updatedAt: Date = new Date(),
        createdAt: Date = new Date()
    ) {
        super(id, updatedAt, createdAt);
        this.login = login;
        this.password = password;
        this.name = name;
        this.profilePictureId = profilePictureId;
        this.profilePicture = profilePicture;
        this.reports = reports;
    }
}