import { DatePipe } from "@angular/common";

export class User{
    email: string;
    name: string;
    phone:string;
    password:string;
    birthDate:DatePipe;
    gender:string;
    country:string;
    image:string;
}