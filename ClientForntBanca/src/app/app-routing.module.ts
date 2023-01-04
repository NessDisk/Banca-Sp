import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientCreateComponent } from './client/components/client-create/client-create.component';
import { ClientGetByIDComponent } from './client/components/client-get-by-id/client-get-by-id.component';
import { LoginComponentComponent } from './login/login-component/login-component.component';
import { InitComponentComponent } from './Navegation/component/init-component/init-component.component';
import { ProductCreateComponent } from './product/components/product-create/product-create.component';
import { ProductGetComponent } from './product/components/product-get/product-get.component';
import { MovStatusComponent } from './product/movement/components/mov-status/mov-status.component';
import { MovTransferComponent } from './product/movement/components/mov-transfer/mov-transfer.component';

const routes: Routes = [{path:'' , component: LoginComponentComponent  },
                        {path:'init' , component: InitComponentComponent },
                        {path:'CreateClient' , component: ClientCreateComponent },
                        {path:'clients' , component: ClientGetByIDComponent },
                        {path:'product/:id' , component: ProductGetComponent },
                        {path:'costumer/:id/createproduct' , component: ProductCreateComponent },
                        {path:'movst/:id' , component: MovStatusComponent },
                        {path:'mov/:id' , component: MovTransferComponent },
                        {path:'**', redirectTo:'', pathMatch:'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
// export const routingcomponents =[ LoginComponentComponent  , InitComponentComponent ];