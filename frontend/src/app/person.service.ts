import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private baseUrl = 'http://localhost:8081/api/persons';

  constructor(private http: HttpClient) {}

  getCountByState(): Observable<[string, number][]> {
    return this.http.get<[string, number][]>(`${this.baseUrl}/countByState`);
  }

  getIMCByAge(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/imcByAge`);
  }

  getPercentageOfObese(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/percentageOfObese`);
  }

  getMediaAge(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/mediaAge`);
  }

  getPossibleDonors(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/possibleDonors`);
  }
}
