import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { Offer } from "../model/Offer";

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})

export class MainpageComponent implements OnInit {
  // to get category and posted date from options
  @ViewChild("category") category: ElementRef = new ElementRef("");
  @ViewChild("postedDate") postedDate: ElementRef = new ElementRef("");
  
  //to view errors
  pageError: string = ""
  showError:boolean = false

  //jwt token
  token: string | null = ""

  constructor(private configService: ConfigService) { }
  
  //offers variable to save the offers retreived from the api
  offers: Offer[] = []

  ngOnInit(): void {
    //get the token
    this.token = localStorage.getItem('token');

    //retrive the offers (default category electronics)
    if (this.token != null) {
      this.configService.getOffers(this.token, "electronics").subscribe((data: Offer[]) => {
        console.log(data);
        this.offers = data;
      },
        error => {
          console.log(error);
          this.pageError = "We encountered some error please try again later"
          this.showError = true
        });
    }
  }

  //on changing category trigger function
  onCategoryChange() {
    let category = this.category.nativeElement.value

    //get the offers filtered by category
    if (this.token != null)
      this.configService.getOffers(this.token, category).subscribe((data: Offer[]) => {
        console.log(data);
        this.offers = data;
      },
        error => {
          if(error.status == 404)
            this.pageError = "no offers found try a different category"
          else
            this.pageError = "We encountered some error please try again later"
          this.showError =true
          console.log(error);
        });
  }

  //retrieve top 3 liked offers
  filterByTopLikes() {
    if (this.token != null)
      this.configService.getOffersByTopLikes(this.token).subscribe((data: Offer[]) => {
        console.log(data);
        this.offers = data;
      },
        error => {
          this.pageError = "We encountered some error please try again later"
          this.showError = true
          console.log(error);
        });
  }

  //filter the posts by the posted date
  filterByPostedDate() {
    let postedDate = this.postedDate.nativeElement.value
    if (this.token != null)
      this.configService.getOffersByPostedDate(this.token, postedDate).subscribe((data: Offer[]) => {
        console.log(data);
        this.offers = data;
      },
        error => {
          console.log(error)
          if (error.status == 400)
            this.pageError = "please enter a valid date"

          else if (error.status == 404)
            this.pageError = "no offers found"
          else
            this.pageError = "We encountered an error please try again later"
          this.showError = true
          console.log(error);
        });
  }

  //show recently liked posts by the user
  showRecentlyLiked() {
    if (this.token != null)
      this.configService.getRecentlyLiked(this.token).subscribe((data: Offer[]) => {
        this.offers = data
      }, error => {
        console.log(error)
        this.pageError = "We encountered some error please try again later"
        this.showError = true;
      })
  }

  closebar(){
    this.showError=false
  }
}
