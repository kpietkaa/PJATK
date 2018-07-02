// Listę unikalnych zawodów;
// Aggreeagate
var cursor = db.people.aggregate([{$group: {_id: "0",jobs: {$addToSet: "$job"}}}])
while(cursor.hasNext()){
	printjsononeline(cursor.next())
}

// Map-Reduce
var map = function() {
	emit(this.job, 1);
}

var reducer = function(key, values) {
	return values.length;
}

db.people.mapReduce(
	map,
	reducer,
	{ out : "zadanie3" }
)

var cursor = db.zadanie3.find();
while(cursor.hasNext()) {
	printjsononeline(cursor.next())
}
