export interface Posts {
    id:number;
    caption:String;
    photo:any;
    likeCount:number;
    comments:String[];
    postUserId:any;
    createdAt:any;
}

export interface NewPost {
    caption:String;
    photo:any;
    postUserId:number
}
