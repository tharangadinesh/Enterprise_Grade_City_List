import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { ToastrModule, ToastContainerModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CityListComponent } from './component/city/city-list/city-list.component';
import { CityDetailsComponent } from './component/city/city-details/city-details.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { HomeComponent } from './component/home/home.component';
import { httpInterceptorProviders } from './_helpers/auth.interceptor';
import { CityAddComponent } from './component/city/city-add/city-add.component';

@NgModule({
  declarations: [
    AppComponent,
    CityDetailsComponent,
    CityListComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    CityAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule,
    ToastrModule.forRoot(
      {positionClass: 'toast-top-right', timeOut: 5000,
          preventDuplicates: true,
          closeButton: true,
          progressBar: true,
          maxOpened: 1,
          autoDismiss: true,
          enableHtml: true},
    ),
    ToastContainerModule,
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
