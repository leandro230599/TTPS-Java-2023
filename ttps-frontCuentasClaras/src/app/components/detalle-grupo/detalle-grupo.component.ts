import { Component } from '@angular/core';
import { Gasto } from '../../models/gasto';
import { ApiService } from '../../services/api.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-detalle-grupo',
  templateUrl: './detalle-grupo.component.html',
  styleUrl: './detalle-grupo.component.css'
})
export class DetalleGrupoComponent {
  id : string;
  datos : any;
  error: string = '';
  listaCatGrupos:any[];
  listaCatGastos:any[];
  listaFD:any[];
  categoria_id:number
  formaDividir_id:number
  fdValor:number = 0;
  status:string;
  classstatus:string;
  username:string;

  constructor(private apiService : ApiService, private router : Router, private route: ActivatedRoute){}

  ngOnInit(): void {
      this.id = this.route.snapshot.paramMap.get('id') || '';
      console.log("La ID es: ",this.id);
      if (this.id != ''){
        this.apiService.getDetallesGrupo(Number(this.id)).subscribe(
          (res) => {
            if (res.hasOwnProperty('mensaje')){
              this.error = res.mensaje;
            } else{
              this.datos = res
            }
          },
          (err) => {
            this.error = "Error al traer detalles del grupo";
          }
        )
        this.apiService.getCategoriasGrupos().subscribe(
          (res) => {
            this.listaCatGrupos=res;
          },
          (err) => {
            console.error("Error al obtener la lista de categorias de grupos");
          }
        )
        this.apiService.getCategoriasGastos().subscribe(
          (res) => {
            this.listaCatGastos=res;
          },
          (err) => {
            console.error("Error al obtener la lista de categorias de gastos");
          }
        )
        this.apiService.getFormaDividir().subscribe(
          (res) => {
            this.listaFD = res;
          },
          (err) => {
            console.error("Error al obtener las lista de formas de dividir gastos");
          }
        )
      }
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
        let nombre = form.value.nombre;
        let idCat = this.categoria_id.toString();
        this.status="Grupo actualizado correctamente.";
        console.log(this.status);
        this.classstatus="alert-success";
        window.scroll(0,0);
        this.apiService.actualizarGrupo(nombre, idCat, Number(this.id)).subscribe( (resp) => {
          console.log("resp");
      })
    }
    else{
      this.status="Datos de formulario no se pudieron validar.";
      console.log(this.status);
      this.classstatus="alert-danger";
    }
  }

  formatearFecha(fecha: string): string {
    const fechaFormateada = new Date(fecha);
    const dia = fechaFormateada.getDate().toString().padStart(2, '0');
    const mes = (fechaFormateada.getMonth() + 1).toString().padStart(2, '0'); // El mes comienza desde 0, por lo que sumamos 1
    const anio = fechaFormateada.getFullYear();
    return `${dia}/${mes}/${anio}`;
  }

  validarFormGasto(form:NgForm){
    if (!form.valid)
      return false;
    if (!(form.value.fecha) && !(form.value.fdValor) && !(form.value.monto) && !(this.categoria_id) && !(this.formaDividir_id))
      return false;
    return true;
  }

  onSubmitLoad(form:NgForm){
    if (this.validarFormGasto(form)){
      let g = new Gasto();
      g.setIdCat(this.categoria_id);
      g.setFecha(form.value.fecha);
      g.setMonto(form.value.monto);
      g.setidFD(this.formaDividir_id);
      g.setfdV(this.fdValor);
      g.setidGrupoPersona(Number(this.id));
      window.scroll(0,0);
      this.apiService.crearGasto(g).subscribe( (resp) => {
        console.log("Gasto creado");
      })
    }
  }

  validarFormAddUser(form:NgForm){
    if (!form.valid)
      return false;
    return true;
  }

  onSubmitAddUser(form:NgForm){
    console.log("Entra?");
    if (this.validarFormAddUser(form)){
      let username:string = this.username;
      console.log(username);
      window.scroll(0,0);
      this.apiService.addUserToGroup(username,Number(this.id)).subscribe( (resp) => {
        console.log("Usuario agregado al grupo");
      })
    }
  }

  soutGasto(){
    console.log("Mostrando datos");
    console.log(this.datos);
  }

  convertirMilisegundosAFecha(milisegundos: number): string {
    const fecha = new Date(milisegundos);
    const dia = fecha.getDate();
    const mes = fecha.getMonth() + 1;
    const anio = fecha.getFullYear();
    return `${dia}/${mes}/${anio}`;
  }

  validarFormUpdateGasto(form:NgForm){
    if (!form.valid)
      return false;
    if (!(form.value.fecha) && !(form.value.fdValor) && !(form.value.monto) && !(this.categoria_id) && !(this.formaDividir_id))
      return false;
    return true;
  }

  onSubmitUpdateSpent(form:NgForm, idGasto:number){
    if (this.validarFormUpdateGasto(form)){
      let g = new Gasto();
      g.setIdCat(this.categoria_id);
      g.setFecha(form.value.fecha);
      g.setMonto(form.value.monto);
      g.setidFD(this.formaDividir_id);
      g.setfdV(this.fdValor);
      g.setidGrupoPersona(Number(this.id));
      window.scroll(0,0);
      this.apiService.actualizarGasto(g, idGasto).subscribe( (resp) => {
        console.log("Gasto actualizado");
      })
    }
  }

  getDeuda(idUser:number, tipo:string):number{
    let monto:number = 0;
    this.apiService.getDeuda(idUser,Number(this.id), tipo).subscribe( (resp) => {
      monto = Number(resp.monto);
    })
    return monto;
  }

}
