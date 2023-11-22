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
  constructor(private rendererObj: Renderer2, private router:Router, private storageService:StorageService, private detailsServive:UserDetailsService) {
    this.getDesignation();
  }

  username:String = this.storageService.getLoggedInUser().username;
  designation:String = "";

  getDesignation(){
    const userId:number = this.storageService.getLoggedInUser().id;
    this.detailsServive.userDetails(userId).subscribe({
      next:(response:AppResponse) => {
        let userDetails:UserDetails = response.data;
        console.log(userDetails.designation);
        this.designation = userDetails.designation;
        localStorage.setItem("details",JSON.stringify(userDetails));
      }
    });
  }

  url = new URL(window.location.href);

  @ViewChild('feed')
  feed!: ElementRef;

  @ViewChild('explore')
  explore!: ElementRef;

  @ViewChild('friends')
  friends!: ElementRef;

  @ViewChild('about')
  about!: ElementRef;

  active(path:String) {
    this.router.navigate([path]);

    this.url = new URL(window.location.href);
    const endpoint = this.url.pathname;

    console.log(endpoint)
    // this.removeClass();

    if(endpoint === "/home"){
      this.rendererObj.addClass(this.feed.nativeElement, 'active');
    }else if(endpoint === "/explore"){
      this.rendererObj.addClass(this.explore.nativeElement, 'active');
    }else if(endpoint === "/friends"){
      this.rendererObj.addClass(this.friends.nativeElement, 'active');
    }else if(endpoint === "/about"){
      this.rendererObj.addClass(this.about.nativeElement, 'active');
    }
  }

  private removeClass(){
    const elements = [this.feed, this.explore, this.friends, this.about]
    elements.forEach(ele => this.rendererObj.removeClass(ele.nativeElement, 'active'));
  }
}
