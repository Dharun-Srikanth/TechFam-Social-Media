import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Form } from '@angular/forms'
import { AppResponse } from 'src/app/model/appResponse';
import { Login } from 'src/app/model/login';
import { AuthService } from 'src/app/service/auth.service';
import { AppUser } from 'src/app/model/appUser';

@Component({
  selector: 'app-login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.css'],
})
export class LoginRegisterComponent {

  constructor(private routerObj : Router, private authService : AuthService){}

  flip(cardName:string, clickName:string) {
    const card = document.getElementById(cardName);
    const para = document.getElementById(clickName);
    console.log(card);
    para?.addEventListener('click', () => card?.classList.toggle('is-flipped'))
  }

  goToHome(){
    this.routerObj.navigate(['/home']);
  }

  username: String = '';
  password: String = '';
  error: String = '';

  login(_loginForm: Form): void {
    let login: Login = {
      username: this.username,
      password: this.password,
    };
    this.authService.login(login).subscribe({
      next: (response: AppResponse) => {
        let user: AppUser = response.data;
        this.authService.setLoggedIn(user);
      },
      error: (err) => {
        console.log(err);

        let message: String = err.error.error.message;
        this.error = message.includes(',') ? message.split(',')[0] : message;
      },
      complete: () => console.log('There are no more action happen.'),
    });
  }



}
