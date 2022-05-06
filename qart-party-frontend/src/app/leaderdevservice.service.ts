import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LeaderdevserviceService {


 //private baseUrl = 'http://localhost:8081/politician-ratings/politics/api/v1/development/get-development-by';
private baseUrl = 'http://localhost:8081/politician-ratings/politics/api/v1/development/getdev';
  constructor(private http: HttpClient) { }

  getDevelopmentList(id:number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
}
