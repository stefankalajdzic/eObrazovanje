import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private getDobaviUplateUrl = environment.baseUrl + '/admin/uplate';

  constructor(public http: HttpClient) {}

  dobaviUplate(): Observable<any> {
    return this.http.get(this.getDobaviUplateUrl);
  }
}
