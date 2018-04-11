// Średnia i łączna ilość środków na kartach kredytowych
// kobiet narodowości polskiej w podziale na waluty.

// Aggregate
// Nie zadziala bo nie przechowujemy w bazie liczb a stringi na tych polach.

var mapper = function() {
  if (!this.credit) return;
  var credits = this.credit;
  for(i in credits) {
    emit(credits[i].currency, parseFloat(credits[i].balance))
  }
}

var reducer = function(key, values) {
  return {
    sum: Array.sum(values),
    avg: Array.sum(values)/values.length
  }
}

db.people.mapReduce(
  mapper,
  reducer,
  {
    out: 'zadanie5',
    query: {
      sex: 'Female',
      nationality: 'Poland'
   }
  }
)
var cursor = db.zadanie5.find();
while(cursor.hasNext()) {
	printjsononeline(cursor.next())
}
