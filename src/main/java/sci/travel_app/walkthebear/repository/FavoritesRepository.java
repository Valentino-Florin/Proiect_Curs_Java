package sci.travel_app.walkthebear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Favorite;
import sci.travel_app.walkthebear.model.entities.Place;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public interface FavoritesRepository extends JpaRepository<Favorite, Long > {
    Favorite findById(long id);
    Favorite findByPlaceAndUser(Place place, AppUser user);
    List<Favorite> findByUser(AppUser user);
    List<Favorite> findByPlace(Place place);
    @Query("select f.place from Favorite f")
    List<Place> getPlacesFromFav();
}

