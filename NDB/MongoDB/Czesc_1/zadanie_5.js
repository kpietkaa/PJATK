var cursor = db.people.find({birth_date: {$gte: '2001-01-01T00:00:0000:00Z'}}, {first_name: 1, last_name: 1, 'location.city': 1})
while(cursor.hasNext()){
	printjsononeline(cursor.next())
}
