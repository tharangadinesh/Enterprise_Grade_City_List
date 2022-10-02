
import { Component, Input, OnInit } from '@angular/core';
import { CityService } from 'app/service/city/city.service';
import { ActivatedRoute, Router } from '@angular/router';
import { City } from 'app/model/city/city.model';

@Component({
  selector: 'app-city-details',
  templateUrl: './city-details.component.html',
  styleUrls: ['./city-details.component.css']
})
export class CityDetailsComponent implements OnInit {

  @Input() viewMode = false;

  @Input() currentCity: City = {
    name: '',
    photo: '',
  };
  
  message = '';

  constructor(
    private cityService: CityService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getCity(this.route.snapshot.params["id"]);
    }
  }

  getCity(id: string): void {
    this.cityService.get(id)
      .subscribe({
        next: (data) => {
          this.currentCity = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updatePublished(status: boolean): void {
    const data = {
      name: this.currentCity.name,
      photo: this.currentCity.photo
    };

    this.message = '';

    this.cityService.update(this.currentCity.id, data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'The status was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  updateCity(): void {
    this.message = '';

    this.cityService.update(this.currentCity.id, this.currentCity)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This city was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  // deleteCity(): void {
  //   this.cityService.delete(this.currentCity.id)
  //     .subscribe({
  //       next: (res) => {
  //         console.log(res);
  //         this.router.navigate(['/cities']);
  //       },
  //       error: (e) => console.error(e)
  //     });
  // }

}