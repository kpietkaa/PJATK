printjson(
	db.people.updateMany(
		{ 'location.city': 'Moscow' },
		{ $set: { 'Moskwa': true } }
	))
