import File from "./file.model";
import BaseEntity from "./base.entity.model";

export default class User extends BaseEntity {
    login: string;
    password: string;
    name: string;
    file: File;

    constructor(
        login: string,
        password: string,
        name: string,
        file: File,
        id: string,
        updatedAt: Date,
        createdAt: Date
    ) {
        super(id, updatedAt, createdAt);
        this.login = login;
        this.password = password;
        this.name = name;
        this.file = file;
    }
}