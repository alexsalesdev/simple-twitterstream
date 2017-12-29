import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { TagCloudModule } from 'angular-tag-cloud-module';
import { AppComponent } from './app.component';
import {WordService} from "./Word/word.service";
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    TagCloudModule,
    HttpClientModule,
    HttpClientJsonpModule
  ],
  providers: [WordService],
  bootstrap: [AppComponent]
})
export class AppModule { }
