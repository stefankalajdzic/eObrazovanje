import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UplateComponent } from './components/uplate/uplate.component';

const routes: Routes = [{ path: 'uplate', component: UplateComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
