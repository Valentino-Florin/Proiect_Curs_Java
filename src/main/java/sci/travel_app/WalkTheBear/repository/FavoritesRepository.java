package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sci.travel_app.WalkTheBear.model.entities.Favorite;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorite, Long > {

}
