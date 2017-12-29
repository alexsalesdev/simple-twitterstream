"use strict";
var Word = (function () {
    function Word(word, amount) {
        this.word = word;
        this.amount = amount;
    }
    // New static method.
    Word.fromJSONArray = function (array) {
        return array.map(function (obj) { return new Word(obj[0], Number(obj[1])); });
    };
    return Word;
}());
exports.Word = Word;
