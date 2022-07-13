//token schema for token validation api
export class TokenResponse{
    constructor(
        public username:string,
        public valid:boolean,
        public empId:number
    ){}
}