//response schema for rest api
export class messageResponse{
    constructor(
        public message:string,
        public status: string,
        public timestamp:Date
    ){}
}