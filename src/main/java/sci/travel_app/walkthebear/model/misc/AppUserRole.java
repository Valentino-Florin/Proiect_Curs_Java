package sci.travel_app.walkthebear.model.misc;

public enum AppUserRole {
    ADMIN(1), TRAVELER(2), HOST(3);

    private int order;
    private AppUserRole(int order) {
    this.order=order;
    }

    public int getOrder() {
        return order;
    }

}
