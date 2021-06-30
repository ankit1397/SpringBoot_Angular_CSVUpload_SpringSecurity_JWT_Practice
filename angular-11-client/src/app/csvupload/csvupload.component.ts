import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { NgxCsvParser, NgxCSVParserError } from 'ngx-csv-parser';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
@Component({
  selector: 'app-csvupload',
  templateUrl: './csvupload.component.html',
  styleUrls: ['./csvupload.component.css']
})
export class CSVUploadComponent implements OnInit {
  currentUser: any;
  isLoggedIn = false;
  content?: string;
  csvRecords: any[] = [];
  csvFile: any;
  colNames: any;

  constructor(private ngxCsvParser: NgxCsvParser, private http: HttpClient, private userService: UserService, private token: TokenStorageService) { }

  @ViewChild('fileImportInput', { static: false }) fileImportInput: any;

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

  fileChangeListener($event: any): void {
    // Select the files from the event
    const files = $event.srcElement.files;
    this.csvFile = $event.target.files[0];
    // Parse the file you want to select for the operation along with the configuration
    this.ngxCsvParser.parse(files[0], { header: false, delimiter: ',' })
      .pipe().subscribe((result: any) => {
        this.csvRecords = result;
        this.colNames = this.csvRecords[0];
        console.log(this.csvRecords);
        console.log(this.colNames);
      }, (error: NgxCSVParserError) => {
        console.log('Error', error);
      });

  }

  uploadToDB() {
    //console.log(this.csvFile);
    const formData: FormData = new FormData();
    formData.append('file', this.csvFile);
    this.userService.uploadCSV(formData).subscribe(
      data => {
        alert("CSV Uploaded successfully!");
        this.content = data;
        console.log(this.content);
      },
      err => {
        this.content = err.error.message;
        alert(this.content);
        console.log(this.content);
      }
    );

  }

}
