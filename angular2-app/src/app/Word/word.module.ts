import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import {WordService} from "./word.service";

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpClientJsonpModule
  ],
  declarations: [],

  providers: [
    WordService
  ],

  exports:[]

})
export class WordModule {
}
