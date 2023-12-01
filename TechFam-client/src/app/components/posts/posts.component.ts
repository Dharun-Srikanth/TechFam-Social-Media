import { Component } from '@angular/core';
import { Form, NgForm } from '@angular/forms';
import { AnimationOptions } from 'ngx-lottie';
import { AppResponse } from 'src/app/model/appResponse';
import { AddComments, ViewComments } from 'src/app/model/comments';
import { Posts } from 'src/app/model/posts';
import { UserDetails } from 'src/app/model/user-details';
import { PostService } from 'src/app/service/post.service';
import { StorageService } from 'src/app/service/storage.service';
import { UserDetailsService } from 'src/app/service/user-details.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css'],
})
export default class PostsComponent {

  options: AnimationOptions = {
    path: '/assets/no-posts.json',
  };

  posts: Posts[] = [];
  isUserLiked:boolean = false;
  comment: String = '';
  id: number = this.storageService.getLoggedInUser().id;

  constructor(
    private postService: PostService,
    private storageService: StorageService,
    private detailsService: UserDetailsService
  ) {
    this.getFollowingPosts();
  }

  getUserDetails() {
    return JSON.parse(localStorage.getItem('details')!);
  }

  isCommentsClicked:boolean = false;
  commentNumber:number = 0;
  likedPosts: number[] = JSON.parse(localStorage.getItem("likedPosts"+this.id)!);

  setUserProfileId(id:number){
    this.storageService.setProfileId(id);
    this.setUserDetails(id);
  }

  userDetails: UserDetails = {
    id: 0,
    profile_picture: null,
    companyName: '',
    designation: '',
    gitHubUrl: '',
    instagramUrl: '',
    linkedInUrl: '',
    youtubeUrl: '',
    userId: 0,
    aboutMe: ''.substring(0, 121),
  };

  setUserDetails(userId: number) {
    this.detailsService.userDetails(userId).subscribe({
      next: (response: AppResponse) => {
        let userDetails: UserDetails = response.data;
        if (userDetails === null) {
          localStorage.setItem('details', JSON.stringify(this.userDetails));
          return;
        }
        localStorage.setItem('details', JSON.stringify(userDetails));
      },
      error: (err) => console.log(err),
      complete: () => console.log('details stored'),
    });
  }

  storeLikedPost(){
    if(!(localStorage.getItem("likedPosts"+this.id)))
      localStorage.setItem("likedPosts"+this.id, JSON.stringify([]));
  }

  getLikedPosts(){
    return this.likedPosts;
  }

  setCommentsClick(postId:number) {
    if(this.isCommentsClicked){
      this.isCommentsClicked = false;
      this.commentNumber = 0;
    }else {
      this.isCommentsClicked = true;
      this.commentNumber = postId;
    }
  }

  addLikes(id: number) {
    this.postService.addLikes(id).subscribe({
      next: (response: AppResponse) => {
        console.log(response.data);
      },
      error: (err) => console.log(err),
      complete: () => {
        this.likedPosts.push(id);
        console.log(this.likedPosts.includes(id));
        
        localStorage.setItem("likedPosts"+this.id, JSON.stringify(this.likedPosts));
        this.getFollowingPosts();
      },
    });
  }

  removeLikes(id: number) {
    this.postService.removeLikes(id).subscribe({
      next: (response: AppResponse) => {
        console.log(response.data);
      },
      error: (err) => console.log(err),
      complete: () => {
        const index = this.likedPosts.indexOf(id);
        this.likedPosts.splice(index,1);
        console.log(this.likedPosts.includes(id));
        
        localStorage.setItem("likedPosts"+this.id, JSON.stringify(this.likedPosts));
        this.getFollowingPosts();
      },
    });
  }

  setLikedPost(){
    if(this.isUserLiked){
      this.isUserLiked = false;
    }else{
      this.isUserLiked = true;
    }
    return this.isUserLiked;
  }

  getFollowingPosts() {
    const userId: number = this.storageService.getLoggedInUser().id;
    this.postService.followingPosts(userId).subscribe({
      next: (response: AppResponse) => {
        this.posts = response.data;
        console.log(this.posts);
      },
      error: (err) => {
        console.log(err);
      },
      complete: () => console.log("There are no more actions"),
    });
  }

  addComments(commentForm: NgForm) {
    let comment: AddComments = {
      postId: commentForm.value.postId,
      comments: commentForm.value.comment,
      userId: this.id,
    };
    this.postService.addComments(comment).subscribe({
      next:(response:AppResponse) => {
        let comments:ViewComments[] = response.data;
        console.log(comments);
      },
      error:(err) => {
        console.log(err);
      },
      complete: () => console.log("There are no more actions"),
    });
  }

  postComments:ViewComments[] = []
  getComments(id:number){
    this.postService.getComments(id).subscribe({
      next:(response:AppResponse) => {
        
        this.postComments = response.data;
        console.log(this.postComments);
        
      },
      error: (err) => console.log(err),
      complete: () => console.log("Completed"),  
    });
  }
}
