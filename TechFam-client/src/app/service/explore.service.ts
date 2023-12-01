import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppResponse } from '../model/appResponse';
import { urlEndpoint } from '../utils/constant';

@Injectable({
  providedIn: 'root'
})
export class ExploreService {

  constructor(private http:HttpClient) { }

  viewExplore(id:number):Observable<AppResponse> {
    return this.http.get<AppResponse>(`${urlEndpoint.baseUrl}/post/explore/${id}`);
  }

}
