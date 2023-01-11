import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule /*, routingcomponents*/ } from './app-routing.module';
import { AppComponent } from './app.component';
import { DemoComponent } from './demo/demo.component';
//components 
import { ClientGetByIDComponent } from './client/components/client-get-by-id/client-get-by-id.component';
import { ClientUpdateComponent } from './client/components/client-update/client-update.component';
import { ClientCreateComponent } from './client/components/client-create/client-create.component';
import { ClientDeleteComponent } from './client/components/client-delete/client-delete.component';
import { ProductCreateComponent } from './product/components/product-create/product-create.component';
import { ProductChangeStatusComponent } from './product/components/product-change-status/product-change-status.component';
import { ProductGetComponent } from './product/components/product-get/product-get.component';
import { MovAddBalanceComponent } from './product/movement/components/mov-add-balance/mov-add-balance.component';
import { MovWithdrawComponent } from './product/movement/components/mov-withdraw/mov-withdraw.component';
import { MovTransferComponent } from './product/movement/components/mov-transfer/mov-transfer.component';

import{HttpClientModule} from '@angular/common/http';
import{FormsModule, ReactiveFormsModule} from '@angular/forms';
import { LoginComponentComponent } from './login/login-component/login-component.component';
import { InitComponentComponent } from './Navegation/component/init-component/init-component.component';
import { RouterModule } from '@angular/router';
import { MovStatusComponent } from './product/movement/components/mov-status/mov-status.component';


@NgModule({
  declarations: [
    AppComponent,
    DemoComponent,
    ClientGetByIDComponent,
    ClientUpdateComponent,
    ClientCreateComponent,
    ClientDeleteComponent,
    ProductCreateComponent,
    ProductChangeStatusComponent,
    ProductGetComponent,
    MovAddBalanceComponent,
    MovWithdrawComponent,
    MovTransferComponent,
    LoginComponentComponent,
    InitComponentComponent,
    MovStatusComponent
    // routingcomponents
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule
    // RouterModule
  ],
  exports: [
    RouterModule,
  ]
  ,

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
