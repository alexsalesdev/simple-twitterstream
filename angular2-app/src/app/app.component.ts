import { Component, OnInit } from '@angular/core';
import { CloudData } from 'angular-tag-cloud-module';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { WordService } from './Word/word.service';
import { Word } from './Word/word.model';

@Component({
  selector: 'app-root',
  template: `
    <angular-tag-cloud [data]="data"></angular-tag-cloud>
    <button (click)="newData()">Get new Data from Observable</button>
  `
})
export class AppComponent implements OnInit {
  ngOnInit(): void {
    let self = this;
    setInterval(function() {
      const changedData$: Observable<CloudData[]> = self.wordService.getWords();
      changedData$.subscribe(res => self.data = res);
    }, 1000);
  }

  constructor(private wordService: WordService) {};

  data: CloudData[] = [
    { text: 'Initial Data weight-10', weight: 10 }
    // ...
  ];

  newData(){


  }
}
