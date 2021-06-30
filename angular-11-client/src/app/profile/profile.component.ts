import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  isLoggedIn = false;
  constructor(private token: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    if(this.currentUser && Object.keys(this.currentUser).length === 0 && this.currentUser.constructor === Object){
      this.isLoggedIn=false;
      console.log(this.currentUser);
    }
    else{
      this.isLoggedIn=true;
      console.log(this.currentUser);
    }
  }

}
