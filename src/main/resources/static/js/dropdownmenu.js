function choice(t){
	var a = ["BAR", "RESTAURANT", "CAFE", "FAST_FOOD", "PASTRY_DESSERTS"];
	var b = ["PARK", "MUSEUM", "ZOO", "H_LANDMARKS", "N_LANDMARKS"];
	var c = ["HOTEL", "MOTEL", "HOSTEL", "GUEST_HOUSE", "CAMPING", "CABIN"];
	 s = document.getElementById('dropdown');
	var sl = s.options.length;
	for(var i = sl-1; i >= 0 ; i--) { s.options[i] = null; }
	if(t.value != 0){
		var z;
		switch (t.value) {
            case "FOODDRINK": z = a; break
			case "ATTRACTIONS" : z = b; break;
			case "LODGING" : z = c; break;
			default : alert('Invalid entry'); break;
		}

		var l = z.length;
		for(i = 0; i < l; i++ ) { s.options[i] = new Option(z[i],z[i],false,false); }
	}
}

