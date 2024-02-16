import { Component } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { NgForm } from '@angular/forms';
import { Grupo } from '../../models/grupo';

@Component({
  selector: 'app-crear-grupo',
  templateUrl: './crear-grupo.component.html',
  styleUrl: './crear-grupo.component.css'
})
export class CrearGrupoComponent {

  status:string;
  classstatus:string;
  lista:any[];
  categoria_id:number

  constructor(private apiService: ApiService){}

  ngOnInit(): void {
    this.apiService.getCategoriasGrupos().subscribe(
      (res) => {
        this.lista=res;
        console.log("La lista es: "+this.lista);
      },
      (err) => {
        console.error("Error al obtener la lista");
      }
    )
  }

  validar(form:NgForm){
    if (!form.valid)
      return false;
    if(!(form.value.nombre && this.categoria_id))
      return false;
    return true;
    }
    
    onSubmit(form:NgForm){
      if (this.validar(form)){
        let g = new Grupo();
        g.setNombre(form.value.nombre);
        g.setCategoriaId(this.categoria_id.toString());
        this.status="Grupo creado exitosamente.";
        console.log(this.status);
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
      this.apiService.crearGrupo(g).subscribe( (resp) => {
        console.log("resp");
      })
    }
    else{
      this.status="Datos de formulario no se pudieron validar.";
      console.log(this.status);
      this.classstatus="alert-danger";
    }
  }
}


/*
{ 
  "nombre":"Nulo",
  "idCat": "1",
  "idUser": "0"
}

http://localhost:8080/ttps-spring/grupo/crearGrupo */