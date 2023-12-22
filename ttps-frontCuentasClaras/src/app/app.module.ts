import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';

import { RouterModule, Routes } from '@angular/router';
import { RegistroComponent } from './components/registro/registro.component';
import { RegistroService } from './services/registro.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NavComponent } from './components/nav/nav.component';
import { AcercaComponent } from './components/acerca/acerca.component';


const appRoutes: Routes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: HomeComponent },
  { path: 'registro', component: RegistroComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegistroComponent,
    NavComponent,
    AcercaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } //<--debugging purposes only
      )
  ],
  providers: [
    RegistroService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
