export class eventSpeakersDTO {
    
    description: string;
    jobTitle: string;
    image: string;
    name: string;
    id: number;
    age:number;

    constructor( description: string, jobTitle: string, image: string, name: string, id: number , age:number) {
        this.description=description;
        this.id=id;
        this.image= image;
        this.jobTitle = jobTitle;
        this.name= name;
        this.age = age;
      }
}