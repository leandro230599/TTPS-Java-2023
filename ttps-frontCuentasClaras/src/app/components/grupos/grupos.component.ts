import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-grupos',
  templateUrl: './grupos.component.html',
  styleUrl: './grupos.component.css'
})
export class GruposComponent implements OnInit{
  grupos: any[] = [];
  error: string = '';

  constructor(private apiService : ApiService, private router : Router){}

  ngOnInit(): void {
      this.apiService.getGrupos().subscribe(
        (res) => {
          if (res.hasOwnProperty('mensaje')){
            this.error = res.mensaje;
          } else{
            this.grupos = res
          }
        },
        (err) => {
          this.error = "Error al traer grupos";
        }
      )
  }

  btnClick(){
    this.router.navigateByUrl('crearGrupo');
  }

  detallesGrupo(grupo_id : number){
    this.router.navigateByUrl('detallesGrupo/'+grupo_id);
  }
}
