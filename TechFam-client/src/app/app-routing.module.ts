import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ExploreComponent } from './components/explore/explore.component';
import { FriendsComponent } from './components/friends/friends.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { AboutComponent } from './components/about/about.component';
import { authGuard } from './guard/auth.guard';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';

const routes: Routes = [
  { path: '', component: LoginRegisterComponent },
  { path: 'home', component: HomeComponent, canActivate: [authGuard]},
  { path: 'explore', component: ExploreComponent, canActivate: [authGuard]},
  { path: 'friends', component: FriendsComponent, canActivate: [authGuard]},
  { path: 'about', component: AboutComponent, canActivate: [authGuard]},
  { path: 'profile', component: ProfilePageComponent, canActivate:[authGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
