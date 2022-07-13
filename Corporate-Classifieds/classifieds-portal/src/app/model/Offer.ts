//offer schema for Offer Object
export class Offer{
    constructor(
        public id:number,
        public name:string,
        public description:string,
        public category:string,
        public openDate:Date,
        public engagedDate:Date,
        public closedDate:Date,
        public likes:number
    ){}
}