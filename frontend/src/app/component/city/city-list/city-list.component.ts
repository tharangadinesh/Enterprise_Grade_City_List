import { Component, OnInit } from '@angular/core';
import { City } from 'app/model/city/city.model';
import { CityService } from 'app/service/city/city.service';
import { StorageService } from 'app/service/storage/storage.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-city-list',
  templateUrl: './city-list.component.html',
  styleUrls: ['./city-list.component.css']
})
export class CityListComponent implements OnInit {

  cities: City[] = [];
  currentCity: City = {};
  currentIndex = -1;
  name: string = '';
  message: string ='';
  isRoleAdmin:boolean = false;
  roles: string[] = [];

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizes = [3, 6, 9];

  constructor(private cityService: CityService, private storageService: StorageService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.retrieveCities();
    this.roles = this.storageService.getUser().roles;
    this.isRoleAdmin = this.roles.includes('ROLE_ALLOW_EDIT');
  }

  getRequestParams(searchName: string, page: number, pageSize: number): any {
    let params: any = {};

    if (searchName) {
      params[`query`] = searchName;
    }

    if (page) {
      params[`page`] = page - 1;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    return params;
  }

  retrieveCities(): void {
    const params = this.getRequestParams(this.name, this.page, this.pageSize);

    this.cityService.getAll(params)
      .subscribe({
        next: (data) => {
          const { items, totalItems } = data;
          this.cities = items;
          this.count = totalItems;
        },
        error: (err) => {
          console.log(err);
        }
      });
  }

  handlePageChange(event: number): void {
    this.page = event;
    this.retrieveCities();
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.retrieveCities();
  }

  refreshList(): void {
    this.retrieveCities();
    this.currentCity = {};
    this.currentIndex = -1;
  }

  setActiveCity(city: City, index: number): void {
    this.currentCity = city;
    this.currentIndex = index;
  }

  searchName(): void {
    this.page = 1;
    this.retrieveCities();
  }

  updateCity(): void {
    this.message = '';

    this.cityService.update(this.currentCity.id, this.currentCity)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.openSuccess( res.message ? res.message : 'This city was updated successfully!' );

          this.refreshList();
        },
        error: (e) => {
          console.error(e)
          this.openError(e);
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
