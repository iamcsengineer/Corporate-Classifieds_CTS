import { Component, ComponentFactoryResolver, OnInit } from '@angular/core';
import { ConfigService } from '../config/config.service';
import { Employee } from "../model/Employee"
import { messageResponse } from '../model/messageResponse';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {
  //jwt token  
  token: String | null = ""

  //employee id
  id: number = 0

  //any page error to udpate the user
  pageError: string = ""

  //employee object to store the employee details
  employee: Employee = new Employee(0, "", "", "", 0, 0, "", 0)

  constructor(private configService: ConfigService) { }

  ngOnInit(): void {
    //retrieving the token and empId from local storage
    this.token = localStorage.getItem('token')
    this.id = Number(localStorage.getItem('userId'))

    //get the details of the user using the httpService
    if (this.token != null) {
      this.configService.getProfile(this.token, this.id).subscribe((data: Employee) => {

        //save the data in employee object
        this.employee = data
        console.log(data)
      }, error => {
        console.log(error)
        this.pageError = "we encountered some error please try again later"
      })
    }
  }

  //update points function call to refresh the points of the user
  updatePoints() {

    //check token and call http service
    if (this.token != null)
      this.configService.updatePoints(this.token, this.id).subscribe((data: messageResponse) => {

        //save the employee's points
        this.employee.pointsGained = Number(data.message.split(" ")[3])
        this.pageError = "points refreshed successfully"
      }, error => {
        console.log(error)
        this.pageError = "we encountered some error please try again later"
      })
  }
}
