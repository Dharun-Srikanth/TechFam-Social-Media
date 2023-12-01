import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppResponse } from '../model/appResponse';
import { urlEndpoint } from '../utils/constant';
import { NewPost, Posts } from '../model/posts';
import { AddComments } from '../model/comments';
import { StorageService } from './storage.service';
import { NgForm } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http:HttpClient, private storageService: StorageService) { }

  followingPosts(id:number):Observable<AppResponse>{
    return this.http.get<AppResponse>(`${urlEndpoint.baseUrl}/post/following-post/${id}`);
  }

  addPost(post:NewPost):Observable<AppResponse>{
    return this.http.post<AppResponse>(`${urlEndpoint.baseUrl}/post/create`,post);
  }

  addNewPost(post:FormData):Observable<AppResponse>{
    return this.http.post<AppResponse>(`${urlEndpoint.baseUrl}/post`,post);
  }

  addComments(comment:AddComments):Observable<AppResponse>{
    return this.http.post<AppResponse>(`${urlEndpoint.baseUrl}/comment/create`, comment);
  }

  getComments(id:number):Observable<AppResponse>{
    return this.http.get<AppResponse>(`${urlEndpoint.baseUrl}/comment/${id}`);
  }

  userPosts(id:number):Observable<AppResponse>{
    return this.http.get<AppResponse>(`${urlEndpoint.baseUrl}/post/${id}`);
  }

  addLikes(id:number):Observable<AppResponse> {
    return this.http.post<AppResponse>(`${urlEndpoint.baseUrl}/post/likes/add/${id}`, id);
  }

  removeLikes(id:number):Observable<AppResponse> {
    return this.http.post<AppResponse>(`${urlEndpoint.baseUrl}/post/likes/remove/${id}`, id);
  }

}
