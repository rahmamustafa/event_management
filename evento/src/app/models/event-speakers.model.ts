export class eventSpeakersDTO {
    
    description: string;
    jobTitle: string;
    image: string;
    name: string;
    id: number;


    constructor( description: string, jobTitle: string, image: string, name: string, id: number) {
        this.description=description;
        this.id=id;
        this.image= image;
        this.jobTitle = jobTitle;
        this.name= name;
      }
}