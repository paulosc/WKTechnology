import { Component } from '@angular/core';
import { UploadService } from '../upload-file.service';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent {
  constructor(private uploadService: UploadService) { }
  responseText: string = '';
  isLoading = false;

  onFileSelected(event: any) {
    this.isLoading = true;
    const file: File = event.target.files[0];
    this.uploadService.uploadFile(file).subscribe(
      (response) => {
        this.responseText = response;
        this.isLoading = false;
      },
      (error) => {
        this.responseText = error.error.text;
        this.isLoading = false;
      }
    );
  }

}
