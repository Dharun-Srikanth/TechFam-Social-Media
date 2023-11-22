import { Component } from '@angular/core';
import { AppResponse } from 'src/app/model/appResponse';
import { Followers, Following } from 'src/app/model/friends';
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
  requests:[] = [];

  constructor(private authService: AuthService, private followingService: FollowingService, private storageService:StorageService) {
    this.getFollowing();
  }

  content: string = 'following';

  navigate(name: string) {
    const option = document.getElementById(name);
    if(name==='following')
      this.getFollowing();
    else if(name==='followers')
      this.getFollowers();
    this.removeClass();
    option?.classList.add('active');
    this.content = name;
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
        this.following = followingUsers;        
      },
      error: (err) => {
        console.log(err);
      },
      complete: () => console.log('There are no more action happen.'),
    });
  }

  getFollowers(){
    const userId:number = this.storageService.getLoggedInUser().id;
    this.followingService.followers(userId).subscribe({
      next: (response:AppResponse) => {
        let followers:Followers[] = response.data;
        console.log(followers);
        this.followers = followers
      },
      error:(err) => {
        console.log(err);
        
      },
      complete: () => console.log("There are no more action happen"),
      
    });
  }

}
