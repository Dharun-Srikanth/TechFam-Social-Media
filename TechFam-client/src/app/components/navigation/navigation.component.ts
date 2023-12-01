import { Component, ElementRef, Renderer2, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AppResponse } from 'src/app/model/appResponse';
import { UserDetails } from 'src/app/model/user-details';
import { StorageService } from 'src/app/service/storage.service';
import { UserDetailsService } from 'src/app/service/user-details.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent {
  constructor(
    private rendererObj: Renderer2,
    private router: Router,
    private storageService: StorageService,
    private detailsServive: UserDetailsService
  ) {
    this.getDesignation();
    this.setUserDetails(this.id)
  }

  id:number = this.storageService.getLoggedInUser().id;

  username: String = this.storageService.getLoggedInUser().username;
  designation: String = JSON.parse(localStorage.getItem('designation')!);

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

  profileId() {
    const id = this.storageService.getLoggedInUser().id;
    this.storageService.setProfileId(id);
    this.setUserDetails(id);
  }

  getDesignation() {
    const userId: number = this.storageService.getLoggedInUser().id;
    this.detailsServive.userDetails(userId).subscribe({
      next: (response: AppResponse) => {
        let userDetails: UserDetails = response.data;
        if (userDetails !== null) {
          let designation:String = userDetails.designation;
          if (designation !== null)
            localStorage.setItem('designation', JSON.stringify(designation));
          else localStorage.setItem('designation', JSON.stringify(""));
        }else{
           localStorage.setItem('designation', JSON.stringify(""));
        }
        localStorage.setItem('details', JSON.stringify(this.userDetails));
      },
      error: (err) => console.log(err),
      complete: () => console.log('Designation taken'),
    });
  }

  setUserDetails(userId: number) {
    this.detailsServive.userDetails(userId).subscribe({
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

  route: String | null = this.storageService.getRoute();

  getUserDetails(){
    return JSON.parse(localStorage.getItem('details')!);
  }

}
