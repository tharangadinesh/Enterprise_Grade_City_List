import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { City } from "../../model/city/city.model";
import { Observable } from "rxjs";

const API_URL = "http://localhost:8080/api/v1/city/";

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private http:HttpClient) {  }

  getAll(params: any): Observable<any> {
    return this.http.get<any>(API_URL + 'all', { params });
  }

  get(id: any): Observable<City> {
    return this.http.get<City>(API_URL + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(API_URL + 'add', data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(API_URL + 'update', data);
  }

}
