import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  uploadCSV(formData: FormData): Observable<any> {   // No need to give any HttpHeaders. Header of required type gets added automatically.
    return this.http.post(API_URL + 'csv/upload', formData);
  }
}
