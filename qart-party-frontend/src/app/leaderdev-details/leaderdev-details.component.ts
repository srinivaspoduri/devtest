import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Development, leaderDevelopments } from '../Developments';
import { LeaderdevserviceService } from '../leaderdevservice.service';
import { FormGroup, FormControl, Validators, FormBuilder }  from '@angular/forms';

@Component({
  selector: 'app-leaderdev-details',
  templateUrl: './leaderdev-details.component.html',
  styleUrls: ['./leaderdev-details.component.css']
})
export class LeaderdevDetailsComponent implements OnInit {

  developments!: any;
  leaderform = this.formBuilder.group({
    name: ''
    
  });

  constructor(private devservice: LeaderdevserviceService,  private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

  onSubmit()
  {
   console.log( this.leaderform.value.name);
   this.devservice.getDevelopmentList( this.leaderform.value.name).subscribe(item=>
    {
      //console.log(item)
      this.developments=item;
      console.log(this.developments);
    
    });

   
  }
}
