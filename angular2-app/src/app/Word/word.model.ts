export class Word {
  constructor(
    public word: String,
    public amount:number
  ){}

  // New static method.
  static fromJSONArray(array: Object[]): Word[] {
    return array.map(obj => new Word(obj[1], Number(obj[0])));
  }
}
