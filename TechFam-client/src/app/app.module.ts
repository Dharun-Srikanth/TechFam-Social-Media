import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { RandomInfoComponent } from './components/random-info/random-info.component';
import { HomeComponent } from './components/home/home.component';
import { PostsComponent } from './components/posts/posts.component';
import { ChatsComponent } from './components/chats/chats.component';
import { FriendSuggestionsComponent } from './components/friend-suggestions/friend-suggestions.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NavigationComponent,
    RandomInfoComponent,
    HomeComponent,
    PostsComponent,
    ChatsComponent,
    FriendSuggestionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
