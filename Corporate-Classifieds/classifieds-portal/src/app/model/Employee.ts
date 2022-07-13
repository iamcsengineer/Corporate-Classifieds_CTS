//employee schema for Employee object
export class Employee{
    constructor(
        public id:number,
        public name:string,
        public department:string,
        public gender:string,
        public age:number,
        public contactNumber:number,
        public email:string,
        public pointsGained:number,
    ){}
}