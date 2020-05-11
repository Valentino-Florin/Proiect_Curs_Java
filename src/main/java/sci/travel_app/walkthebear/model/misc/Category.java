package sci.travel_app.walkthebear.model.misc;

public enum Category {
    ATTRACTIONS ("Tourist attractions"), FOODDRINK ("Food and drink"), LODGING("Lodging");

    private final String displayValue;
    Category(String displayValue) {
        this.displayValue = displayValue;
    }
    public String getDisplayValue() {
        return displayValue;
    }
}
