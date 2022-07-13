import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from "@angular/forms";
import { Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConfigService } from '../config/config.service';
import { messageResponse } from '../model/messageResponse';
import { Offer } from '../model/Offer';

@Component({
  selector: 'app-add-offer',
  templateUrl: './add-offer.component.html',
  styleUrls: ['./add-offer.component.css']
})

export class AddOfferComponent implements OnInit {
  //offer form
  addOfferForm: FormGroup = new FormGroup({})

  //error handling
  pageError: String = ""

  //offer object to save the offer details
  offer: Offer = new Offer(0, "", "", "", new Date(), new Date(), new Date(), 0)

  //jwt token
  token: string | null = ""

  constructor(private configService: ConfigService,private route:Router) { }

  ngOnInit(): void {

    //retrieve the token
    this.token = localStorage.getItem("token")

    //initiate the form
    this.addOfferForm = new FormGroup({
      description: new FormControl("", [
        Validators.required,
        Validators.minLength(10)
      ]),
      name: new FormControl("", [
        Validators.required,
        Validators.minLength(3)
      ]),
      category: new FormControl("Plants", [
        Validators.required
      ])
    })
  }

  get name() { return this.addOfferForm.get('name') }
  get description() { return this.addOfferForm.get('description') }
  get category() { return this.addOfferForm.get('category') }

  onSubmit() {
    console.log(this.addOfferForm)

    //update the offer values
    this.offer.name = this.addOfferForm.value.name
    this.offer.description = this.addOfferForm.value.description
    this.offer.category = this.addOfferForm.value.category
    
    //save by calling the service for rest api
    if (this.token != null)
      this.configService.addOffer(this.token, this.offer).subscribe((data: messageResponse) => {
        this.route.navigate(["/myOffers"])
      },error=>{
        console.log(error)
        this.pageError = "We encountered an error please try again later"
      })
  }
}
