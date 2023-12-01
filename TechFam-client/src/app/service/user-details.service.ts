import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, endWith } from 'rxjs';
import { AppResponse } from '../model/appResponse';
import { urlEndpoint } from '../utils/constant';
import { UserDetails } from '../model/user-details';
import { StorageService } from './storage.service';
import { UserVal } from '../model/appUser';

@Injectable({
  providedIn: 'root',
})
export class UserDetailsService {
  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) {}

  userDetails(id: number): Observable<AppResponse> {
    return this.http.get<AppResponse>(`${urlEndpoint.baseUrl}/details/${id}`);
  }

  editDetails(details: UserDetails): Observable<AppResponse> {
    return this.http.post<AppResponse>(
      `${urlEndpoint.baseUrl}/details/edit`,
      details
    );
  }

  addDetails(detail:FormData):Observable<AppResponse> {
    return this.http.post<AppResponse>(`${urlEndpoint.baseUrl}/details`, detail);
  }

  user(id:number) {
    return this.http.get<AppResponse>(`${urlEndpoint.baseUrl}/details/user/${id}`);
  }

  editUser(values: UserVal){
    return this.http.post<AppResponse>(`${urlEndpoint.baseUrl}/details/user/edit`, values);
  }
}
