export interface Following {
    id:number;
    followingUser:any;
}

export interface Followers {
    id:number;
    following:any;
}

export interface requests {
    id:number,
    following: any
}

export interface FollowersId{
    id:number
}

export interface addFollowing {
    userId: number,
    followingUserId:number
}

