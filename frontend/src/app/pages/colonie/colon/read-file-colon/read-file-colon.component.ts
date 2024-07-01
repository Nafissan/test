import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Colon } from '../../shared/model/colon.model';

@Component({
  selector: 'fury-read-file-colon',
  templateUrl: './read-file-colon.component.html',
  styleUrls: ['./read-file-colon.component.scss']
})
export class ReadFileColonComponent implements OnInit {
  @Input() colon: Colon;
  @Input() fileType: 'ficheSocial' | 'document'; // New input to specify file typ
  pdfDataUrl: SafeResourceUrl; // Safe URL for the PDF file
  showFrame: boolean = true; // Boolean to control the frame display

  constructor(private sanitizer: DomSanitizer) { }


  ngOnInit(): void {
    try {
      if (this.colon && this.fileType) {
        const fileBase64 = this.colon[this.fileType];
        if (this.isValidBase64(fileBase64)) {
          const binaryString = atob(fileBase64);
          const byteNumbers = new Uint8Array(binaryString.length);
          for (let i = 0; i < binaryString.length; i++) {
            byteNumbers[i] = binaryString.charCodeAt(i);
          }
          const blob = new Blob([byteNumbers], { type: 'application/pdf' });
          this.pdfDataUrl = this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(blob));
          console.log(this.pdfDataUrl);
        }
      }
    } catch (error) {
      console.error('Error decoding base64 string:', error);
    }
  }
  isValidBase64(base64: string): boolean {
    try {
      atob(base64);
      return true;
    } catch (e) {
      return false;
    }
  }
  ngOnDestroy(): void {
    if (this.pdfDataUrl) {
      const objectUrl = this.pdfDataUrl as string;
      URL.revokeObjectURL(objectUrl);
    }
  }

  closeFrame(): void {
    this.showFrame = false;
  }
}