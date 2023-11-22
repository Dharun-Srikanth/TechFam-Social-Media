import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppResponse } from '../model/appResponse';
import { HttpClient } from '@angular/common/http';
import { urlEndpoint } from '../utils/constant';

@Injectable({
  providedIn: 'root'
})
export class FollowingService {

  constructor(private http : HttpClient) { }

  following(id:number): Observable<AppResponse> {
    return this.http
      .get<AppResponse>(`${urlEndpoint.baseUrl}/following/${id}`);
  }

  followers(id:number):Observable<AppResponse> {
    return this.http
        .get<AppResponse>(`${urlEndpoint.baseUrl}/followers/${id}`);
  }
}
