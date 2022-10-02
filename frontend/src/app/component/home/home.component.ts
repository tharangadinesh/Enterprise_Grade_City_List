import { Component, OnInit } from '@angular/core';
import { StorageService } from 'app/service/storage/storage.service'; 
import { AuthService } from 'app/service/auth/auth.service';
import { Router, CanActivate } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn = false;

  constructor(
    private storageService: StorageService, 
    private authService: AuthService, 
    private router: Router
    ) { }

  ngOnInit(): void {

    this.isLoggedIn = this.storageService.isLoggedIn();    
    
    if (!this.isLoggedIn) {
      this.router.navigate(['login']);
    }
  }

}
