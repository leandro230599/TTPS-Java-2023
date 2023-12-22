import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../models/usuario';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment.prod';

/*const httpOptions = {
  headers: new HttpHeaders({
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, DELETE'
  })
};*/

@Injectable({
  providedIn: 'root'
})

export class RegistroService {
  apiURL = environment.apiUrl;

  constructor(private http: HttpClient) { }

  crearUsuario(user:Usuario):Observable<Usuario>{
      console.log("Usuario en servicio registro: "+JSON.stringify(user));
      return this.http.post<Usuario>(`${this.apiURL}/usuario/registrarUsuario`, user);
  }
}
