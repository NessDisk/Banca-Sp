import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Movement } from '../../models/moviment';
import { MovementServices } from '../../services/movement.service';

@Component({
  selector: 'app-mov-status',
  templateUrl: './mov-status.component.html',
  styleUrls: ['./mov-status.component.css']
})
export class MovStatusComponent implements OnInit {

  productId: number;

  movement:Movement[] =[];


  constructor(private movService: MovementServices, private route: ActivatedRoute){ }

  // constructor(private productService: ProductServices, private route: ActivatedRoute){ }
  ngOnInit(): void {
    
    this.productId = this.route.snapshot.params['id'];
this.loadClient();
  }


  loadClient():void{
    this.movService.MovementByIdProduct( this.productId).subscribe(
      (response) =>{
        if(response.peticionExitosa){
          this.movement = response.datos;
          console.log(this.movement)
        }
      },err =>{
        console.log(err)
      }
    );
  }

}
