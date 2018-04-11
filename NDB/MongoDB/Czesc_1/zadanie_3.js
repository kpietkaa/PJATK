var cursor = db.people.find({nationality: 'Germany', sex: 'Male'})
while(cursor.hasNext()){
	printjsononeline(cursor.next())
}
