export class Report {

    reasons: string[];
    description: string;

    constructor(
        reasons: string[] = [],
        description: string = ''
    ) {
        this.reasons = reasons;
        this.description = description;
    }
    
}