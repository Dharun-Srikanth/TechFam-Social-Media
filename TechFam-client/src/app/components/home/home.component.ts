import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {

  currentPage():String{
    const url = new URL(window.location.href);
    const endpoint = url.pathname;
    return endpoint;
  }
}
