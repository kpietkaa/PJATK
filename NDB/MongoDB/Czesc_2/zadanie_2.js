// Łączną ilość środków pozostałych na kartach kredytowych osób w bazie,
// w podziale na waluty;

// Aggregate
// > db.people.aggregate({$group: {_id: "$credit.currency", sum: {$sum: "$credit.balance"}}})
// { "_id" : [ "IDR", "HTG", "CNY", "XOF" ], "sum" : 0 }
// { "_id" : [ "CUP", "PEN" ], "sum" : 0 }
// ...
// Nie zadziala bo nie przechowujemy w bazie liczb a stringi na tych polach.

var mapper = function() {
  if (!this.credit) return;
  var credits = this.credit;
  for(i in credits) {
    emit(credits[i].currency, parseFloat(credits[i].balance))
  }
}

var reducer = function(key, values) {
  return Array.sum(values)
}

db.people.mapReduce(
  mapper,
  reducer,
  { out: 'zadanie2' }
)
var cursor = db.zadanie2.find();
while(cursor.hasNext()) {
	printjsononeline(cursor.next())
}
