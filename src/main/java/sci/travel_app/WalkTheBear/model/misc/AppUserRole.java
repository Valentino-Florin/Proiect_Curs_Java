package sci.travel_app.WalkTheBear.model.misc;

import java.util.Optional;

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
