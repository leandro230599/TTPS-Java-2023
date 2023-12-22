import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Usuario } from '../../models/usuario';
import { RegistroService } from '../../services/registro.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {
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

  constructor(private registroService: RegistroService) { }

  ngOnInit() {

  }

  validar(form:NgForm){
    if (!form.valid)
      return false;
    if(!(form.value.email && form.value.password && form.value.nombre && form.value.apellido && form.value.username))
      return false;
    return true;
    }
    
    onSubmit(form:NgForm){
      if (this.validar(form)){
        let u = new Usuario();
        console.log(form.value);
        u.setLastName(form.value.apellido);
        u.setEmail(form.value.email);
        u.setFirstName(form.value.nombre);
        u.setPassword(form.value.password);
        u.setUsername(form.value.username);
        this.status="Registro satisfactorio.";
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
      this.registroService.crearUsuario(u).subscribe( (resp) => {
        console.log("resp");
      })
    }
    else{
      this.status="Datos de formulario no se pudieron validar.";
      this.classstatus="alert-danger";
    }
  }

}
