import { Component } from '@angular/core';
import { StorageService } from './service/storage/storage.service';
import { AuthService } from './service/auth/auth.service';
import { Router, CanActivate } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent {

  private roles: string[] = [];
  isLoggedIn = false;
  username?: string;

  constructor(
    private storageService: StorageService, 
    private authService: AuthService, 
    private router: Router
    ) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;
      this.username = user.username;
      
      this.router.navigate(['home']);
    } else {
      this.router.navigate(['login']);
    }
  }

  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.clean();
        this.router.navigate(['/login']);
        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
