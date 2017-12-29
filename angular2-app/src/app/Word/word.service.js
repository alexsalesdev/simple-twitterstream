"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
/* * * ./app/comments/services/comment.service.ts * * */
// Imports
var core_1 = require('@angular/core');
var Rx_1 = require('rxjs/Rx');
// Import RxJs required methods
require('rxjs/add/operator/map');
require('rxjs/add/operator/catch');
var WordService = (function () {
    // private commentsUrl = 'http://578f454de2fa491100415d08.mockapi.io/api/Comment';
    // Resolve HTTP using the constructor
    function WordService(http) {
        this.http = http;
        // private instance variable to hold base url
        this.baseUrl = 'http://localhost:9000/';
        this.wordsUrl = this.baseUrl + 'api/wordcount';
    }
    // Fetch all existing comments
    WordService.prototype.getWords = function () {
        // ...using get request
        return this.http.get(this.wordsUrl)
            .map(function (res) {
            console.log(res);
            return res;
        })
            .catch(function (error) { return Rx_1.Observable.throw(error.json().error || 'Server error'); });
    };
    WordService = __decorate([
        core_1.Injectable()
    ], WordService);
    return WordService;
}());
exports.WordService = WordService;
