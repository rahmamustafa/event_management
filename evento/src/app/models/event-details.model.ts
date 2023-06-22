export class eventDetailsDTO {
    eventDate: Date;
    description: string;
    title: string;
    isOnline: boolean;
    location: string;
    image: any;
    // id :number;


    constructor(eventDate: Date, description: string, title: string, isOnline: boolean, image: any) {
        this.eventDate = eventDate;
        this.description = description;
        this.title = title;
        this.isOnline = isOnline;
        this.image = image;
      }
}