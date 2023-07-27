import User from "./user.model";

export class Audit {

    id: string;
    entity: string;
    entityId: string;
    action: string;
    user: User;
    json: string;

    constructor(
        id: string = '',
        entity: string = '',
        entityId: string = '',
        action: string = '',
        user: User = new User(),
        json: string = ''
    ) {
        this.id = id;
        this.entity = entity;
        this.entityId = entityId;
        this.action = action;
        this.user = user;
        this.json = json;
    }

}