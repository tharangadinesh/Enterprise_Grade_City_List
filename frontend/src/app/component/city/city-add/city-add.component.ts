import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CityService } from 'app/service/city/city.service';
import { Toast, ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-city-add',
  templateUrl: './city-add.component.html',
  styleUrls: ['./city-add.component.css']
})
export class CityAddComponent implements OnInit {

  form : any = {
    name : null,
    photo : null
  };

  constructor(
    private cityService :CityService, 
    private router: Router, 
    private toastr: ToastrService
    ) { }

  ngOnInit(): void {

  }
  
  onSubmit(): void {

    const data = this.form;
    
    this.cityService.create(data).subscribe({
      next: data => {
        console.log(data);
        this.router.navigateByUrl('/home');
        this.openSuccess("Successfuly added");
      },
      error: err => {
        this.openError(err.error.message);
      }
    });
  }

  openSuccess(message : string){

    this.toastr.success('Success !', message);
  }

  openError(message : string){

    this.toastr.error('Error !', message);
  }

}
