import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Usuario } from '../../models/usuario';
import { ApiService } from '../../services/api.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  status:string;
  classstatus:string;
  
  /*formValue = {
    tipo: 'v',
    nombre: ''
  }

  formValueVete = {
    tipo: 'v',
    nombre: ''
  }*/

  constructor(private apiService: ApiService) { }

  ngOnInit() {

  }

  validar(form:NgForm){
    if (!form.valid)
      return false;
    if(!(form.value.mail && form.value.password))
      return false;
    return true;
    }
    
    onSubmit(form:NgForm){
      if (this.validar(form)){
        let mail = form.value.mail;
        let password = form.value.password;
        this.status="Logeo exitoso                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          .";
        this.classstatus="alert-success";
      // this.registroService.crearUsuario(u).subscribe(user => u, (err:HttpErrorResponse) => {
      //   console.log("El error es: "+err.status);
      //   if(err.status == 409){
      //     this.status="El email ya esta registrado en el sistema.";
      //     this.classstatus="alert-danger";
      //   }
      //   else{
      //     this.status="Error desconocido.";
      //     this.classstatus="alert-danger";
      //   }
      // });
      window.scroll(0,0);
      this.apiService.login(mail,password).subscribe( (resp) => {
        //console.log("resp");                                                                                                                                               
      });
    }
    else{
      this.status="Datos de formulario no se pudieron validar.";
      this.classstatus="alert-danger";
    }
  }

}
