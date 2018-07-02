printjson(db.people.insert({
	"_id": "1",
	"sex" : "Male",
	"first_name" : "Kazimierz",
	"last_name" : "Pietka",
	"job" : "Software Developer",
	"email" : "s17407@pja.edu.pl",
	"location" : {
		"city" : "Wroclaw",
		"address" : {
			"streetname" : "Ulica",
			"streetnumber" : "1"
		}
	},
	"description" : "Lorem ipsum dolor amet disrupt occupy twee hella, letterpress readymade art party brunch affogato.",
	"height" : "199",
	"weight" : "199",
	"birth_date" : "1993-07-07T06:66:66Z",
	"nationality" : "Polish",
	"credit" : [
		{
			"type" : "switch",
			"number" : "1159888939100098611",
			"currency" : "EUR",
			"balance" : "666.66"
		}
	]
}))