package sci.travel_app.walkthebear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sci.travel_app.walkthebear.model.entities.Favorite;

public interface FavoritesRepository extends JpaRepository<Favorite, Long > {

}
