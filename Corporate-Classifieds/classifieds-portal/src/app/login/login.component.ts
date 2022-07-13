import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import { ConfigService } from '../config/config.service';
import { AuthResponse } from '../model/authResponse';
import { User } from '../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  constructor(private configService: ConfigService,private router:Router) { }

  //user object to save user form details
  user: User = { username: "", password: "" }

  //reactive form for login
  userForm: FormGroup = new FormGroup({})

  //to display errors
  formError = ""

  ngOnInit(): void {

    //initialize the form
    this.userForm = new FormGroup({
      username: new FormControl(this.user.username, [
        Validators.required,
        Validators.minLength(3)
      ]),
      password: new FormControl(this.user.password, [
        Validators.required,
      ])
    })
  }

  get username() { return this.userForm.get('username') }
  get password() { return this.userForm.get('password') }

  //on submitting the form
  onSubmit() {
    console.log(this.userForm.value)
    
    let userDetails = { "empUsername": this.userForm.value.username, "empPassword": this.userForm.value.password, "empid": 0 }
    
    //retrive the data from the authmicroservice
    this.configService.getUserToken(userDetails).subscribe((data:AuthResponse)=>{
      
      //save the token in local storage
      localStorage.setItem("token",data["authToken"])
      
      //save the user id in local storage
      localStorage.setItem("userId",data['empid'])
      
      //navigate to the main page of the user
      this.router.navigate(['main'])
    },
    error =>{
      this.formError = "Credentials are incorrect"
      console.log(error)
    });

  }
}
