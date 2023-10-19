import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadFileComponent } from './upload-file/upload-file.component';
import { DisplayInfoComponent } from './display-info/display-info.component';

const routes: Routes = [
  { path: 'upload', component: UploadFileComponent },
  { path: 'display-info', component: DisplayInfoComponent },
  { path: '', redirectTo: '/upload', pathMatch: 'full' }, // Rota padrão redirecionada para o componente de upload
  { path: '**', redirectTo: '/upload' }, // Rota curinga redirecionada para o componente de upload
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
