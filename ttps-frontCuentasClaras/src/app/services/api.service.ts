import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Usuario } from '../models/usuario';
import { Grupo } from '../models/grupo';
import { Gasto } from '../models/gasto';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment.prod';
import { map } from 'rxjs/operators';

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

export class ApiService {
  apiURL = environment.apiUrl;

  constructor(private http: HttpClient) { }

  crearUsuario(user:Usuario):Observable<Usuario>{
      console.log("Usuario en servicio registro: "+JSON.stringify(user));
      return this.http.post<Usuario>(`${this.apiURL}/usuario/registrarUsuario`, user);
  }

  login(
    mail: string,
    password: string
  ){
    return this.http.post<any>(`${this.apiURL}/login/auth`, {
      mail: mail,
      password: password,
    }).pipe(map(credenciales => {
      if (credenciales && credenciales.token){
        localStorage.setItem('currentUser', JSON.stringify(credenciales.token));
      }
      return credenciales;
    }));
  }

  logout(){
    localStorage.removeItem('currentUser');
  }

  getToken(){
    let token = localStorage.getItem('currentUser');
    if (token){
      token = JSON.parse(token);
    }
    console.log("token en getToken es "+token);
    return token;
  }

  getGrupos(){
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    return this.http.get<any>(`${this.apiURL}/grupo/listarGrupos`, {headers});
  }

  getCategoriasGrupos(){
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    return this.http.get<any>(`${this.apiURL}/categoria/obtenerCategoriaGrupos`, {headers});
  }

  getCategoriasGastos(){
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    return this.http.get<any>(`${this.apiURL}/categoria/obtenerCategoriaGasto`, {headers});
  }

  crearGrupo(grupo:Grupo):Observable<Grupo>{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    return this.http.post<Grupo>(`${this.apiURL}/grupo/crearGrupo`, grupo, {headers});
  }

  actualizarGrupo(nombre:string, idCat:string, grupo_id:number):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    const data = {
      nombre: nombre,
      idCat: idCat
    }
    return this.http.put<any>(`${this.apiURL}/grupo/actualizar/${grupo_id}`, data, {headers});
  }

  getDetallesGrupo(grupo_id:number){
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    return this.http.get<any>(`${this.apiURL}/grupo/detallesGrupo/${grupo_id}`, {headers});
  }

  getFormaDividir(){
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    return this.http.get<any>(`${this.apiURL}/formadividir/gastos`, {headers});
  }

  crearGasto(gasto:Gasto):Observable<Gasto>{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    return this.http.post<Gasto>(`${this.apiURL}/grupo/crearGastoGrupo`, gasto, {headers});
  }

  actualizarGasto(gasto:Gasto, idGasto:number):Observable<Gasto>{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    const data = {
      gasto: gasto,
      idGasto: idGasto
    }
    return this.http.put<Gasto>(`${this.apiURL}/grupo/editarGastoGrupo`, data, {headers});
  }

  addUserToGroup(username:string, id:number):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    const data = {
      username: username,
      id: id
    }
    return this.http.post<any>(`${this.apiURL}/grupo/agregarAGrupo`, data, {headers});
  }

  getDeuda(idUser:number,id:number, tipo:string):Observable<any>{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer '+ this.getToken(),
    });
    const data = {
      idUser: idUser,
      idGrupoPersona: id,
      tipo: tipo
    }
    return this.http.post<any>(`${this.apiURL}/usuario/obtenerDeuda`, data, {headers});
  }

}
