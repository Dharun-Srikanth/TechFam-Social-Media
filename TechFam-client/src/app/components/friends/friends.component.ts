import { Component } from '@angular/core';
import { AppResponse } from 'src/app/model/appResponse';
import { Followers, Following, requests } from 'src/app/model/friends';
import { AuthService } from 'src/app/service/auth.service';
import { FollowingService } from 'src/app/service/following.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css'],
})
export class FriendsComponent {
  following:Following[] = [];
  followers:Followers[] = [];
  requests:requests[] = [];
  followersId: number[] = [];

  constructor(private authService: AuthService, private followingService: FollowingService, private storageService:StorageService) {
    this.getFollowing();
    this.getRequests();
    this.getFollowersId();
  }

  content: string = 'following';

  navigate(name: string) {
    const option = document.getElementById(name);
    if(name==='following')
      this.getFollowing();
    else if(name==='followers')
      this.getFollowers();
    else if(name==='requests')
      this.getRequests();

    this.removeClass();
    option?.classList.add('active');
    this.content = name;
  }

  getRequestCount():number{
    // this.getRequests();
    return this.requests.length;
  }

  removeClass() {
    const following = document.getElementById('following');
    const followers = document.getElementById('followers');
    const request = document.getElementById('requests');
    const allOptions = [following, followers, request];
    console.log(allOptions);

    allOptions.forEach((option) => option?.classList.remove('active'));
  }

  getFollowing(){
    const userId:number = this.storageService.getLoggedInUser().id;
    this.followingService.following(userId).subscribe({
      next: (response: AppResponse) => {
        let followingUsers: Following[] = response.data;
        console.log(followingUsers);
        localStorage.setItem("followingCount"+userId, JSON.stringify(followingUsers.length));
        this.following = followingUsers;        
      },
      error: (err) => {
        console.log(err);
      },
      complete: () => console.log('Following complete'),
    });
  }

  getFollowers(){
    const userId:number = this.storageService.getLoggedInUser().id;
    this.followingService.followers(userId).subscribe({
      next: (response:AppResponse) => {
        let followers:Followers[] = response.data;
        console.log(followers);
        localStorage.setItem("followersCount"+userId, JSON.stringify(followers.length));
        this.followers = followers
      },
      error:(err) => {
        console.log(err);
        
      },
      complete: () => console.log("Followers complete"),
      
    });
  }

  getRequests(){
    const userId:number = this.storageService.getLoggedInUser().id;
    this.followingService.requests(userId).subscribe({
      next: (response:AppResponse) => {
        this.requests = response.data;
        console.log(this.requests);        
      },
      error:(err) => console.log(err),
      complete:() => console.log("Request Completed"),
    });
  }

  acceptRequest(id:number) {
    console.log(id);
    
    this.followingService.acceptRequest(id).subscribe({
      next: (response: Object) => {
        console.log(response);
      }
    });
    this.getRequests();
  }

  removeFollowing(id:number){
    console.log(id);
    
    this.followingService.removeFollowing(id).subscribe({
      next: (response: AppResponse) => {
        console.log(response.data);
      },
      error: (err) => console.log(err),
      complete: () => console.log("unfollow successful")
      
      
    });
    this.getFollowing();
  }

  removeFollowers(id:number, followingId:number){
    this.followingService.removeFollowers(followingId).subscribe({
      next: (response: AppResponse) => {
        console.log(response.data + "removed"); 
      },
      error: (err) => console.log(err),
      complete: () => this.getFollowers()
    });
    console.log(followingId);
    
    this.removeFollowing(id);
  }

  getFollowersId(){
    this.followingService.followersId().subscribe({
      next: (response:AppResponse) => {
        this.followersId = response.data;
      },
      error: (err) => console.log(err),
      complete: () => console.log(this.followersId)
    });
  }

}
