import { ThisReceiver, ThrowStmt } from '@angular/compiler';
import { ifStmt } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConfigService } from '../config/config.service';
import { messageResponse } from '../model/messageResponse';
import { Offer } from "../model/Offer";

@Component({
  selector: 'app-offer-details',
  templateUrl: './offer-details.component.html',
  styleUrls: ['./offer-details.component.css']
})

export class OfferDetailsComponent implements OnInit {

  //offer object to save the offer details 
  offer: Offer = new Offer(0, "offer name", "offer description", "category", new Date(), new Date(), new Date(), 0)
  
  //to toggle the like button
  isLiked: boolean = false

  //to save the offer id
  id: number = 0

  //to save the employee id
  empId: number = 0

  //jwt token
  token: string | null = ""

  //to handle error
  pageError: String = ""

  //config service : httpClient , route : to retrive the id param 
  constructor(private route: ActivatedRoute, private configService: ConfigService) { }

  ngOnInit(): void {
    //retrieve token and ids
    this.token = localStorage.getItem("token")
    this.id = Number(this.route.snapshot.paramMap.get('id'))
    this.empId = Number(localStorage.getItem("userId"))

    //call the service which calls the rest apis
    if (this.token != null && this.id != 0)
      this.configService.getOfferDetailsById(this.token, this.id).subscribe((data: Offer) => {
        
        //save the details in the offer object
        this.offer = data
      })
  }

  //function to toggle the like button 
  likedIt() {
    this.isLiked = !this.isLiked
  }

  //save user like
  submitLike() {
    if (this.token != null)
      this.configService.saveLike(this.token, this.id).subscribe((data) => {
        this.pageError = "your like is saved successfully"
        this.isLiked = false;
        this.offer.likes += 1
      },error=>{
        console.log(error)
        this.pageError = "Some error occured please try again later"
      })
  }

  //engage a user 
  engageUser() {
    //call the rest api
    if (this.token != null) {
      this.configService.engageOffer(this.token, this.offer.id, this.empId).subscribe((data: messageResponse) => {
        
        if(data.status == "BAD_REQUEST"){
          this.pageError = data.message
        }else{
          window.location.reload()
        }
        console.log(data)
      }, error => {
        console.log(error);
        this.pageError = "Some error occured please try again later"
      })
    }
  }
}
