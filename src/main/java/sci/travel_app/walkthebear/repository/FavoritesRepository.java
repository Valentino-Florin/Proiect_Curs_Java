package sci.travel_app.walkthebear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Favorite;
import sci.travel_app.walkthebear.model.entities.Place;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorite, Long > {
    Favorite findById(long id);
    Favorite findByPlaceAndUser(Place place, AppUser user);
    List<Favorite> findByUser(AppUser user);
    List<Favorite> findByPlace(Place place);

}

