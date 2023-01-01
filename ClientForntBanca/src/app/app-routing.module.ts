import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientCreateComponent } from './client/components/client-create/client-create.component';
import { ClientGetByIDComponent } from './client/components/client-get-by-id/client-get-by-id.component';
import { LoginComponentComponent } from './login/login-component/login-component.component';
import { InitComponentComponent } from './Navegation/component/init-component/init-component.component';

const routes: Routes = [{path:'' , component: LoginComponentComponent  },
                        {path:'init' , component: InitComponentComponent },
                        {path:'CreateClient' , component: ClientCreateComponent },
                        {path:'**', redirectTo:'', pathMatch:'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
// export const routingcomponents =[ LoginComponentComponent  , InitComponentComponent ];