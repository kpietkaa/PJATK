var jsons = require('./jsons.js')

var tab = [];
function test(p) {
    var credits = p;
    for(i in credits) {
      tab.push(parseFloat(credits[i].balance))
    }
  console.log(tab);
  }

test(jsons.long_json.credit);

