import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppResponse } from '../model/appResponse';
import { HttpClient } from '@angular/common/http';
import { urlEndpoint } from '../utils/constant';
import { FriendsComponent } from '../components/friends/friends.component';
import { StorageService } from './storage.service';
import { Following, addFollowing } from '../model/friends';

@Injectable({
  providedIn: 'root'
})
export class FollowingService {

  constructor(private http : HttpClient, private storageService: StorageService) {}

  following(id:number): Observable<AppResponse> {
    return this.http
      .get<AppResponse>(`${urlEndpoint.baseUrl}/following/${id}`);
  }

  followers(id:number):Observable<AppResponse> {
    return this.http
        .get<AppResponse>(`${urlEndpoint.baseUrl}/followers/${id}`);
  }

  addFollowing(user:addFollowing):Observable<AppResponse> {
    return this.http.post<AppResponse>(`${urlEndpoint.baseUrl}/following/add`, user);
  }

  requests(id:number):Observable<AppResponse> {
    return this.http.get<AppResponse>(`${urlEndpoint.baseUrl}/following/request/${id}`);
  }

  acceptRequest(id:number):Observable<Object> {
    return this.http.post(`${urlEndpoint.baseUrl}/following/accept/${id}`, id);
  }

  removeFollowing(id:number):Observable<AppResponse> {
    return this.http.delete<AppResponse>(`${urlEndpoint.baseUrl}/following/remove/${id}`);
  }

  removeFollowingByUserId(id:number, fId:number):Observable<AppResponse> {
    return this.http.delete<AppResponse>(`${urlEndpoint.baseUrl}/following/remove/${id}/${fId}`);
  }

  removeFollowers(id:number):Observable<AppResponse> {
    return this.http.delete<AppResponse>(`${urlEndpoint.baseUrl}/followers/remove/${id}`);
  }

  followersId():Observable<AppResponse> {
    const id = this.storageService.getLoggedInUser().id;
    return this.http.get<AppResponse>(`${urlEndpoint.baseUrl}/followers/followersId/${id}`);
  }

  friendSuggestion():Observable<AppResponse> {
    const id = this.storageService.getLoggedInUser().id;
    return this.http.get<AppResponse>(`${urlEndpoint.baseUrl}/following/suggestion/${id}`);
  }
}
