import { Component } from '@angular/core';
import { AnimationOptions } from 'ngx-lottie';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {

  options: AnimationOptions = {
    path: '/assets/about1.json',
  };

  options2: AnimationOptions = {
    path: '/assets/about2.json',
  };

  options3: AnimationOptions = {
    path: '/assets/backend.json',
  };
  
}
