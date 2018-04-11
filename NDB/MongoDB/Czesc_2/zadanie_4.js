// Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie,
// w podziale na narodowości;

var mapper = function() {
  if (!this.weight) return;
  if (!this.height) return;
  emit(this.nationality, { count:1,
    weight: parseFloat(this.weight),
    height: parseFloat(this.height)})
}

var reducer = function(key, values) {
  var reducedVal = { count: 0, weight: 0, height: 0, max_bmi: 0, min_bmi: 1000000 }
  for (idx in values) {
    reducedVal.height += values[idx].height;
    reducedVal.weight += values[idx].weight;
    var bmi = reducedVal.weight/reducedVal.height/reducedVal.height;
    if (bmi > reducedVal.max_bmi) {
      reducedVal.max_bmi = bmi;
    }
    if (bmi < reducedVal.min_bmi) {
      reducedVal.min_bmi = bmi;
    }
    reducedVal.count += values[idx].count;
  }
  return reducedVal;
}

var finalizeFunction = function(key, reducedVal) {
  return {
    bmi: reducedVal.weight/reducedVal.height/reducedVal.height,
    min_bmi: reducedVal.min_bmi,
    max_bmi: reducedVal.max_bmi
  }
}

db.people.mapReduce(
  mapper,
  reducer,
  {
    out: 'zadanie4',
    finalize: finalizeFunction
  }
)
var cursor = db.zadanie4.find();
while(cursor.hasNext()) {
  printjsononeline(cursor.next())
}
