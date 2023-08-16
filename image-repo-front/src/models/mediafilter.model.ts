export class MediaFilter {

    public name: string;
    public description: string;
    public tags: string[];

    constructor(
        name: string = '',
        description: string = '',
        tags: string[] = []
    ) {
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

}