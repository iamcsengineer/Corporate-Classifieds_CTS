//response schema for rest api
export class AuthResponse{
    constructor(
        public username:string,
        public authToken:string,
        public empid:string
        ){}
}