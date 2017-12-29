"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
require('rxjs/add/observable/of');
var AppComponent = (function () {
    function AppComponent(wordService) {
        this.wordService = wordService;
        this.data = [
            { text: 'Initial Data weight-10', weight: 10 }
        ];
    }
    AppComponent.prototype.ngOnInit = function () {
    };
    ;
    AppComponent.prototype.newData = function () {
        this.wordService.getWords();
        // const changedData$: Observable<CloudData[]> = Observable.of([
        //   { text: 'Weight-3', weight: 3 },
        //   // ...
        // ]);
        // changedData$.subscribe(res => this.data = res);
    };
    AppComponent = __decorate([
        core_1.Component({
            selector: 'app-root',
            template: "\n    <angular-tag-cloud [data]=\"data\"></angular-tag-cloud>\n    <button (click)=\"newData()\">Get new Data from Observable</button>\n  "
        })
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
