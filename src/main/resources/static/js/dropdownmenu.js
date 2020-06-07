function choice(t){
	var a = ["Bar", "Restaurant", "Cafe", "Fast Food", "Pastry & Desserts"];
	var b = ["Park", "Museum", "Zoo", "Historical Landmarks & Architecture", "Natural Landmarks"];
	var c = ["Hotel", "Motel", "Hostel", "Guesthouse", "Camping", "Cabin"];
	s = document.getElementById('second');
	var sl = s.options.length;
	for(var i = sl-1; i >= 0 ; i--) { s.options[i] = null; }
	if(t.value != 0){
		var z;
		switch (t.value) {
			case 'Food and drink' : z = a; break;
			case 'Tourist attractions' : z = b; break;
			case 'Lodging' : z = c; break;
			default : alert('Invalid entry'); break;
		}
		var l = z.length;
		for(i = 0; i < l; i++ ) { s.options[i] = new Option(z[i],z[i],false,false); }
	}
}

