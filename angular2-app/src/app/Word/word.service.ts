/* * * ./app/comments/services/comment.service.ts * * */
// Imports
import { Injectable }     from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {} from '@angular/http';
import {Observable} from 'rxjs/Rx';

// Import RxJs required methods
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Word} from "./word.model";
import { CloudData } from 'angular-tag-cloud-module';
@Injectable()
export class WordService {
  // private instance variable to hold base url
  private baseUrl = 'http://localhost:9000/';
  private wordsUrl = this.baseUrl + 'api/wordcount';
  // private commentsUrl = 'http://578f454de2fa491100415d08.mockapi.io/api/Comment';
  // Resolve HTTP using the constructor
  constructor (private http: HttpClient) {}

  // Fetch all existing comments
  getWords() : Observable<CloudData[]>{
    console.log("getWords:  " + this.wordsUrl);
    // ...using get request
    return this.http.get(this.wordsUrl)
    // ...now we return data
      .map(res => Word.fromJSONArray(res).map(word => {
        return {text: word.word, weight: word.amount};
      }) )
      // ...errors if any
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

}
