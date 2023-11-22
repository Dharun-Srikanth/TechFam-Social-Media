import { Component } from '@angular/core';
import { AppResponse } from 'src/app/model/appResponse';
import { Posts } from 'src/app/model/posts';
import { PostService } from 'src/app/service/post.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent {
  posts: Posts[] = [];

  constructor(private postService:PostService, private storageService:StorageService){
    this.getFollowingPosts();
  }

  getFollowingPosts(){
    const userId:number = this.storageService.getLoggedInUser().id;
    this.postService.followingPosts(userId).subscribe({
      next:(response:AppResponse) => {
        this.posts = response.data;
        console.log(this.posts);
        
      }
    })
  }
}
