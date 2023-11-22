import { Component } from '@angular/core';
import { AnimationOptions } from 'ngx-lottie';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  options: AnimationOptions = {
    path: '/assets/NotificationV3/notification-V3.json',
  };

  constructor(private authObj : AuthService){}

  logout(){
    this.authObj.logout();
  }
}
