import { Component } from '@angular/core';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {

  constructor(private storageService: StorageService){
    this.storeProfileId();
  }

  currentPage():String{
    const url = new URL(window.location.href);
    const endpoint = url.pathname;
    return endpoint;
  }

  storeProfileId(){
    localStorage.setItem("profileId", JSON.stringify(this.storageService.getLoggedInUser().id));
  }
}
