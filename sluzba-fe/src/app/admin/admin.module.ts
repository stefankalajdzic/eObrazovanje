import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AngularMaterialModule } from '../angular-material/angular-material.module';
import { FormsModule } from '@angular/forms';
import { UplateComponent } from './components/uplate/uplate.component';

@NgModule({
  declarations: [UplateComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    AngularMaterialModule,
    FormsModule,
  ],
})
export class AdminModule {}
