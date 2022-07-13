import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomepageComponent } from './homepage/homepage.component';
import {HttpClientModule} from "@angular/common/http";
import { MainpageComponent } from './mainpage/mainpage.component';
import { OfferDetailsComponent } from './offer-details/offer-details.component';
import { ProfileComponent } from './profile/profile.component';
import { MyOffersComponent } from './my-offers/my-offers.component';
import { OfferEditComponent } from './offer-edit/offer-edit.component';
import { AddOfferComponent } from './add-offer/add-offer.component';
import { ContactUsComponent } from './contact-us/contact-us.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    HomepageComponent,
    MainpageComponent,
    OfferDetailsComponent,
    ProfileComponent,
    MyOffersComponent,
    OfferEditComponent,
    AddOfferComponent,
    ContactUsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
