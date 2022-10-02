import { Component, OnInit } from '@angular/core';
import { AuthService } from 'app/service/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {
    username : null,
    password:null,
    role: []
  };

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  roles = [];

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.intRoles();
  }

  onSubmit(): void {
    const { username, password, role } = this.form;
    
    let userRoles = [role];

    this.authService.register(username, password, userRoles).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }
  
  intRoles(): void{

    this.authService.getUserRoles().subscribe({
      next : data =>{
        this.roles = data;
      },
      error: err => {
        this.errorMessage = err.error.message;
      }
    });
  }
}
