import { Component } from '@angular/core';
import { AppResponse } from 'src/app/model/appResponse';
import { UserVal } from 'src/app/model/appUser';
import { Following, addFollowing } from 'src/app/model/friends';
import { UserDetails } from 'src/app/model/user-details';
import { FollowingService } from 'src/app/service/following.service';
import { StorageService } from 'src/app/service/storage.service';
import { UserDetailsService } from 'src/app/service/user-details.service';

@Component({
  selector: 'app-friend-suggestions',
  templateUrl: './friend-suggestions.component.html',
  styleUrls: ['./friend-suggestions.component.css']
})
export class FriendSuggestionsComponent {
  suggesstions:UserVal[] = []
  suggestionList:UserVal[] = [];
  id:number = this.storageService.getLoggedInUser().id;

  constructor(private followingService: FollowingService, private storageService: StorageService, private detailsService: UserDetailsService) {
    this.getFriendSuggestion();
    this.storeAddFriends();
  }

  storeAddFriends(){
    if(!localStorage.getItem("addFriend"+this.id)){
      localStorage.setItem("addFriend"+this.id, JSON.stringify([]));
    }
  }

  friendAdded:number[] = JSON.parse(localStorage.getItem("addFriend"+this.id)!);

  getFriendSuggestion() {
    this.followingService.friendSuggestion().subscribe({
      next: (response: AppResponse) => {
        this.suggesstions = response.data;
        // this.suggesstions = this.suggesstions.filter((val1) => {
        //   this.friendAdded.map((val2) => val1.id !== val2);
        // });
        this.suggestionList = this.suggesstions.slice(0,4);
        console.log(this.suggestionList);
        
      },
      error: (err) => console.log(err),
      complete: () => console.log("Friend Suggestion completed")    
    });
  }

  setUserProfileId(id:number){
    this.storageService.setProfileId(id);
    this.setUserDetails(id);
  }

  setUserDetails(userId:number){
    this.detailsService.userDetails(userId).subscribe({
      next:(response:AppResponse) => {
        let userDetails:UserDetails = response.data;
        localStorage.setItem("details",JSON.stringify(userDetails));
      },
      error: (err) => console.log(err),
      complete: () => console.log("details stored")
    });
  }

  addFriend(userId:number, followingId:number){
    let value:addFollowing = {
      userId: userId,
      followingUserId: followingId
    }
    this.followingService.addFollowing(value).subscribe({
      next: (response: AppResponse) => {
        this.friendAdded.push(followingId);
        localStorage.setItem("addFriend"+this.id,JSON.stringify(this.friendAdded));
      },
      error: (err) => console.log(err),
      complete: () => console.log("Friend added")  
    })
  }

  unfollowFriend(userId:number, fId:number){
    this.followingService.removeFollowingByUserId(userId, fId).subscribe({
      next: (response:AppResponse) => {
        const index = this.friendAdded.indexOf(userId);
        this.friendAdded.splice(index,1);
        localStorage.setItem("addFriend"+this.id, JSON.stringify(this.friendAdded));
      },
      error: (err) => console.log(err),
      complete: () => console.log("Friend removed") 
    });
  }
}
