import { Component } from '@angular/core';
import { Form, NgForm } from '@angular/forms';
import { AnimationOptions } from 'ngx-lottie';
import { AppResponse } from 'src/app/model/appResponse';
import { NewPost, Posts } from 'src/app/model/posts';
import { UserDetails } from 'src/app/model/user-details';
import { AuthService } from 'src/app/service/auth.service';
import { PostService } from 'src/app/service/post.service';
import { StorageService } from 'src/app/service/storage.service';
import { ProfilePageComponent } from '../profile-page/profile-page.component';
import { UserDetailsService } from 'src/app/service/user-details.service';
import { requests } from 'src/app/model/friends';
import { FollowingService } from 'src/app/service/following.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  options: AnimationOptions = {
    path: '/assets/no-notification.json',
  };

  constructor(
    private authObj: AuthService,
    private postService: PostService,
    private storageService: StorageService,
    private detailsService: UserDetailsService,
    private followingService:FollowingService
  ) {
    this.getRequests();
  }

  username: String = this.storageService.getLoggedInUser().username;

  notification = [1, 2, 3, 4, 5];

  logout() {
    this.authObj.logout();
  }

  setProfileId() {
    const id = this.storageService.getLoggedInUser().id;
    localStorage.setItem('profileId', JSON.stringify(id));
    this.setUserDetails(id);
  }

  // post image upload
  uploadFile() {
    const fileInput = document.getElementsByClassName('file-input');
    const droparea = document.getElementsByClassName('file-drop-area');
    const deleteBtn = document.getElementsByClassName('item-delete');
  }

  setUserDetails(userId: number) {
    this.detailsService.userDetails(userId).subscribe({
      next: (response: AppResponse) => {
        let userDetails: UserDetails = response.data;
        localStorage.setItem('details', JSON.stringify(userDetails));
      },
      error: (err) => console.log(err),
      complete: () => console.log('details stored'),
    });
  }

  caption: String = '';
  id: number = this.storageService.getLoggedInUser().id;

  addPost(_postForm: NgForm): void {
    // let post: NewPost = {
    //   caption: this.caption,
    //   photo: null,
    //   postUserId: this.id,
    // };
    // this.postService.addPost(post).subscribe({
    //   next: (response: AppResponse) => {
    //     let posts: Posts = response.data;
    //     console.log(posts);
    //   },
    //   error: (err) => {
    //     console.log(err);
    //   },
    //   complete: () => console.log('There are no more action'),
    // });


    const formData = new FormData();
    formData.append('photo', this.file);
    formData.append('postUserId', this.id.toString());
    formData.append('caption', _postForm.value.caption);

    this.postService.addNewPost(formData).subscribe({
      next: (response: AppResponse) => {
        console.log(response.data);
        
        _postForm.reset();
      },
      complete: () => {},
      error: (error: Error) => {
        console.log('Message:', error.message);
        console.log('Name:', error.name);
      },
    });
  }

  requests: requests[] = [];
  getRequests() {
    const userId: number = this.storageService.getLoggedInUser().id;
    this.followingService.requests(userId).subscribe({
      next: (response: AppResponse) => {
        this.requests = response.data;
        console.log(this.requests);
      },
      error: (err) => console.log(err),
      complete: () => console.log('Request Completed'),
    });
  }

  file = '';

  onFileChange(event: any) {
    const fileInput = event.target;
    if (fileInput && fileInput.files.length > 0) {
      this.file = fileInput.files[0];

      // console.log('Selected file',this.file);
    }
  }

  getUserDetails(){
    return JSON.parse(localStorage.getItem('details')!);
  }
}
