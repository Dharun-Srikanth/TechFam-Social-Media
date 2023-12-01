import { Component } from '@angular/core';
import { Form, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AppResponse } from 'src/app/model/appResponse';
import { AppUser, UserVal } from 'src/app/model/appUser';
import { AddComments, ViewComments } from 'src/app/model/comments';
import { Posts } from 'src/app/model/posts';
import { UserDetails } from 'src/app/model/user-details';
import { FollowingService } from 'src/app/service/following.service';
import { PostService } from 'src/app/service/post.service';
import { StorageService } from 'src/app/service/storage.service';
import { UserDetailsService } from 'src/app/service/user-details.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css'],
})
export class ProfilePageComponent {
  constructor(
    private details: UserDetailsService,
    private storageService: StorageService,
    private postService: PostService,
    private friends: FollowingService
  ) {
    this.getUser(this.userId);
    this.setAllDetails(this.userId);
    this.getUserPosts(this.userId);
    this.storeLikedPost();
    this.getComments(2);
  }

  userId: number = JSON.parse(localStorage.getItem('profileId')!);
  id: number = this.storageService.getLoggedInUser().id;

  followingCount: number = JSON.parse(
    localStorage.getItem('followingCount' + this.userId)!
  );
  followersCount: number = JSON.parse(
    localStorage.getItem('followersCount' + this.userId)!
  );

  isEditEnabled: boolean = false;
  posts: Posts[] = [];
  isUserLiked: boolean = false;
  isCommentsClicked: boolean = false;
  likedPosts: number[] = JSON.parse(
    localStorage.getItem('likedPosts' + this.id)!
  );

  uploadImage() {
    const inputTag = document.getElementById('upload-dp');
    inputTag?.click();
  }

  url = '';
  preview = '';
  onSelectFile(event: any) {
    const fileInput = event.target;
    if (fileInput && fileInput.files.length > 0) {
      this.preview = fileInput.files[0];
      // console.log('Selected file',this.file);
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url

      reader.onload = (event: any) => {
        // called once readAsDataURL is completed
        this.url = event.target.result;
      };
    }
  }

  updateDP(form: NgForm) {
    const formData = new FormData();
    formData.append('profile_picture', this.preview);
    formData.append('companyName', this.getUserDetails().companyName);
    formData.append('designation', this.getUserDetails().designation);
    formData.append('gitHubUrl', this.getUserDetails().gitHubUrl);
    formData.append('youtubeUrl', this.getUserDetails().youtubeUrl);
    formData.append('linkedInUrl', this.getUserDetails().linkedInUrl);
    formData.append('instagramUrl', this.getUserDetails().instagramUrl);
    formData.append('aboutMe', this.getUserDetails().aboutMe);
    formData.append('id', this.getUserDetails().id.toString());
    formData.append('detailsUser', this.id.toString());

    console.log(formData);

    this.details.addDetails(formData).subscribe({
      next: (response: AppResponse) => {
        console.log(response.data);
        localStorage.setItem('details', JSON.stringify(response.data));
        this.setAllDetails(this.userId);
      },
      error: (err) => console.log(err),
      complete: () => console.log('photo uploaded'),
    });
  }

  storeLikedPost() {
    if (!localStorage.getItem('likedPosts' + this.id))
      localStorage.setItem('likedPosts' + this.id, JSON.stringify([]));
  }

  getLikedPosts() {
    // this.likedPosts = JSON.parse(localStorage.getItem("likedPosts"+this.id)!);
    return this.likedPosts;
  }

  setCommentsClick(postId: number) {
    if (this.isCommentsClicked) {
      this.isCommentsClicked = false;
      this.commentNumber = 0;
    } else {
      this.isCommentsClicked = true;
      this.commentNumber = postId;
    }
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

  email: String = '';
  fname: String = '';
  lname: String = '';

  user: UserVal = {
    id: 0,
    email: '',
    name: '',
    username: '',
  };

  setAllDetails(userId: number) {
    this.details.userDetails(userId).subscribe({
      next: (response: AppResponse) => {
        let userDetails: UserDetails = response.data;
        if (userDetails === null) {
          localStorage.setItem('details', JSON.stringify(this.userDetails));
        } else {
          localStorage.setItem('details', JSON.stringify(userDetails));
        }
      },
      error: (err) => console.log(err),
      complete: () => console.log('details stored'),
    });
    if (localStorage.getItem('details') !== null) {
      this.userDetails = JSON.parse(localStorage.getItem('details')!);
      console.log(this.userDetails);
    } else {
      this.userDetails = {
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
    }

    if (localStorage.getItem('userValues') !== null) {
      this.user = JSON.parse(localStorage.getItem('userValues')!);
      if (this.user.name != null)
        if (this.user.name.split(' ').length > 1) {
          this.fname = this.user.name.split(' ')[0];
          this.lname = this.user.name.split(' ')[1];
        } else {
          this.fname = this.user.name;
          console.log(this.user);
        }
    } else {
      console.log('Else user');

      this.user = {
        id: 0,
        email: '',
        name: '',
        username: '',
      };
    }
  }

  getUserDetails() {
    return JSON.parse(localStorage.getItem('details')!);
  }

  getUser(id: number) {
    this.details.user(id).subscribe({
      next: (response: AppResponse) => {
        this.user = response.data;
        localStorage.setItem('userValues', JSON.stringify(this.user));
      },
      error: (err) => console.log(err),
    });
  }

  goToLink(link: String | undefined) {
    console.log(link);
    if (link) window.open(link.toString(), '_blank');
  }

  editUser(detailsForm: NgForm) {
    this.user = {
      id: this.storageService.getLoggedInUser().id,
      email: detailsForm.value.email,
      name: this.fname + ' ' + this.lname,
      username: detailsForm.value.username,
    };
    this.details.editUser(this.user).subscribe({
      next: (response: AppResponse) => {
        this.user = response.data;
        localStorage.setItem('userValues', JSON.stringify(this.user));
        this.setAllDetails(this.userId);
      },
    });
  }

  editDetails(detailsForm: NgForm) {
    console.log(detailsForm.value);
    this.userDetails = {
      id: this.storageService.getLoggedInUser().id,
      profile_picture: null,
      companyName: this.userDetails.companyName,
      designation: this.userDetails.designation,
      gitHubUrl: this.userDetails.gitHubUrl,
      instagramUrl: this.userDetails.instagramUrl,
      linkedInUrl: this.userDetails.linkedInUrl,
      youtubeUrl: this.userDetails.youtubeUrl,
      userId: this.storageService.getLoggedInUser().id,
      aboutMe: this.userDetails.aboutMe,
    };

    this.details.editDetails(this.userDetails).subscribe({
      next: (response: AppResponse) => {
        console.log('edit stored');
        localStorage.setItem('details', JSON.stringify(response.data));
        this.setAllDetails(this.userId);
        this.disableEdit();
      },
      error: (err) => console.log(err),
      complete: () => console.log('Edit details completd'),
    });

    this.editUser(detailsForm);
  }

  enableEdit() {
    this.isEditEnabled = true;
  }

  disableEdit() {
    this.isEditEnabled = false;
  }

  cancel() {
    this.isEditEnabled = false;
    this.setAllDetails(this.userId);
  }

  comment: String = '';

  addComments(commentForm: NgForm) {
    let comment: AddComments = {
      postId: commentForm.value.postId,
      comments: commentForm.value.comment,
      userId: this.id,
    };
    this.postService.addComments(comment).subscribe({
      next: (response: AppResponse) => {
        let comments: ViewComments[] = response.data;
        console.log(comments);
      },
      error: (err) => {
        console.log(err);
      },
      complete: () => console.log('There are no more actions'),
    });
  }

  postComments: ViewComments[] = [];
  commentNumber: number = 0;
  getComments(id: number) {
    this.postService.getComments(id).subscribe({
      next: (response: AppResponse) => {
        this.postComments = response.data;
      },
      error: (err) => console.log(err),
      complete: () => console.log('Completed'),
    });
  }

  getUserPosts(id: number) {
    this.postService.userPosts(id).subscribe({
      next: (response: AppResponse) => {
        this.posts = response.data;
        console.log('Posts: ' + this.posts);
      },
      error: (err) => {
        console.log(err);
      },
      complete: () => console.log('User Post Completed'),
    });
  }

  scrollToPosts() {
    let ele = document.getElementById('user-posts');
    console.log(ele);

    ele?.scrollIntoView({ behavior: 'smooth', block: 'start' });
    this.getUserPosts(this.id);
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

        localStorage.setItem(
          'likedPosts' + this.id,
          JSON.stringify(this.likedPosts)
        );
        this.getUserPosts(this.userId);
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
        this.likedPosts.splice(index, 1);
        console.log(this.likedPosts.includes(id));

        localStorage.setItem(
          'likedPosts' + this.id,
          JSON.stringify(this.likedPosts)
        );
        this.getUserPosts(this.userId);
      },
    });
  }

  setLikedPost() {
    if (this.isUserLiked) {
      this.isUserLiked = false;
    } else {
      this.isUserLiked = true;
    }
    return this.isUserLiked;
  }
}
