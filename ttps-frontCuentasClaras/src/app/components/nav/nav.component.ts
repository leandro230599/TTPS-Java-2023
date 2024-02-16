import { Component, OnInit, ViewChild, AfterViewInit, OnChanges } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})

export class NavComponent {

  title = 'Cuentas Claras';

  constructor(
    private router: Router,
    private apiService: ApiService) { }

  logout() {
    this.apiService.logout();
    this.router.navigate(['/index']);
}
}
