import { NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';

import { RouterModule, Routes } from '@angular/router';
import { RegistroComponent } from './components/registro/registro.component';
import { ApiService } from './services/api.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NavComponent } from './components/nav/nav.component';
import { AcercaComponent } from './components/acerca/acerca.component';
import { GruposComponent } from './components/grupos/grupos.component';
import { AmigosComponent } from './components/amigos/amigos.component';
import { CrearGrupoComponent } from './components/crear-grupo/crear-grupo.component';
import { DetalleGrupoComponent } from './components/detalle-grupo/detalle-grupo.component';


const appRoutes: Routes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: HomeComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'login', component: LoginComponent },
  { path: 'grupos', component: GruposComponent },
  { path: 'amigos', component: AmigosComponent },
  { path: 'crearGrupo', component: CrearGrupoComponent},
  { path: 'detallesGrupo/:id', component: DetalleGrupoComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegistroComponent,
    NavComponent,
    AcercaComponent,
    LoginComponent,
    GruposComponent,
    AmigosComponent,
    CrearGrupoComponent,
    DetalleGrupoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false } //<--debugging purposes only
      )
  ],
  providers: [
    ApiService
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
