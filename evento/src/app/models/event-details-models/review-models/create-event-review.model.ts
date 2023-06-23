export class CreateEventReview{
    userId:number;
    review:String;
    constructor(userId:number ,review:string){
        this.review = review;
        this.userId =userId;
    }

}