package sci.travel_app.walkthebear.service;

import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Favorite;
import sci.travel_app.walkthebear.model.entities.Place;

import java.util.List;

public interface FavoritesService {

    void addToFavorites (Place place, AppUser user);
    void removeFavorite (Place place, AppUser user);
    List<Favorite> getFavsForPlace(Place place);
    boolean isAdded(Place place, AppUser user);


}
