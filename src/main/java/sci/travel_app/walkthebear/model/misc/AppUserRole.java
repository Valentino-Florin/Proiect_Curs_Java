package sci.travel_app.walkthebear.model.misc;

public enum AppUserRole {
    ADMIN("Admin"), TRAVELER("Traveler"), HOST("Host");

    private final String displayRole ;

    AppUserRole(String displayRole) {
        this.displayRole = displayRole;
    }

    public String getDisplayRole() {
        return displayRole;
    }

}

