// Średnią wagę i wzrost osób w bazie z podziałem na płeć
// (tzn. osobno mężczyzn, osobno kobiet);

// Aggregate
// > db.people.aggregate({$group: {_id: "$sex", avg_height: {$avg: "$height"}, avg_weight: {$avg: "$weight"}}})
// { "_id" : "Female", "avg_height" : null, "avg_weight" : null }
// { "_id" : "Male", "avg_height" : null, "avg_weight" : null }
// Nie zadziala bo nie przechowujemy w bazie liczb a stringi na tych polach.

var mapper = function() {
  emit(this.sex, { count: 1,
    weight: parseFloat(this.weight),
    height: parseFloat(this.height)})
}

var reducer = function(key, values) {
  var reducedVal = { count: 0, weight: 0, height: 0 }
  for (idx in values) {
    reducedVal.count += values[idx].count;
    reducedVal.weight += values[idx].weight;
    reducedVal.height += values[idx].height;
  }
  return reducedVal;
}

var finalizeFunction = function(key, reducedVal) {
  return {
    weight: reducedVal.weight/reducedVal.count,
    height: reducedVal.height/reducedVal.count
  }
}

db.people.mapReduce(
  mapper,
  reducer,
  {
    out: 'zadanie1',
    finalize: finalizeFunction
  }
)
var cursor = db.zadanie1.find();
while(cursor.hasNext()) {
  printjsononeline(cursor.next())
}
