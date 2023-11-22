import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { RandomInfoComponent } from './components/random-info/random-info.component';
import { HomeComponent } from './components/home/home.component';
import { PostsComponent } from './components/posts/posts.component';
import { ChatsComponent } from './components/chats/chats.component';
import { FriendSuggestionsComponent } from './components/friend-suggestions/friend-suggestions.component';
import { ExploreComponent } from './components/explore/explore.component';
import { FriendsComponent } from './components/friends/friends.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AboutComponent } from './components/about/about.component';
import { LoaderInterceptorService } from './service/interceptor/loaderInterceptor.service';
import { AuthInterceptorService } from './service/interceptor/authInterceptor.service';
import { AddPostComponent } from './components/add-post/add-post.component';
import { LottieModule } from 'ngx-lottie';
import player from 'lottie-web';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';


export function playerFactory() {
  return player;
}

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NavigationComponent,
    RandomInfoComponent,
    HomeComponent,
    PostsComponent,
    ChatsComponent,
    FriendSuggestionsComponent,
    ExploreComponent,
    FriendsComponent,
    LoginRegisterComponent,
    AboutComponent,
    AddPostComponent,
    ProfilePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    LottieModule.forRoot({ player: playerFactory }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoaderInterceptorService,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
