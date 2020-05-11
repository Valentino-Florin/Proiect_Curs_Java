package sci.travel_app.walkthebear.model.misc;

public enum SubCategory {
    BAR ("Bar"), RESTAURANT("Restaurant"), CAFE ("Cafe"), FAST_FOOD ("Fast Food"), PASTRY_DESSERTS("Pastry & Desserts"), PARK ("Park"), MUSEUM("Museum"), ZOO ("Zoo"), H_LANDMARKS("Historical Landmarks & Architecture"), N_LANDMARKS("Natural Landmarks"), HOTEL("Hotel"), MOTEL("Motel"), HOSTEL("Hostel"), GUEST_HOUSE ("Guesthouse"), CAMPING ("Camping"), CABIN ("Cabin");


    private final String displayValue;
    SubCategory(String displayValue) {
        this.displayValue = displayValue;
    }
    public String getDisplayValue() {
        return displayValue;
    }
}


//FoodDrink
// BAR, RESTAURANT, CAFE, FAST_FOOD, PASTRY_DESSERTS
//Attractions
// PARK, MUSEUM, ZOO, H_LANDMARKS, N_LANDMARKS
//Lodging
//  HOTEL, MOTEL, HOSTEL, GUEST_HOUSE, CAMPING, CABIN
