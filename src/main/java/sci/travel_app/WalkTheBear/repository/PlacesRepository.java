<<<<<<< Updated upstream
package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.misc.Category;

import java.util.List;

@Repository
public interface PlacesRepository extends CrudRepository<Place, Long> {
    List<Place> findByName(String name);
    Place findById(long id);

=======
package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.misc.Category;

import java.util.List;

@Repository
public interface PlacesRepository extends CrudRepository<Place, Long> {
    List<Place> findByName(String name);
    Place findById(long id);

>>>>>>> Stashed changes
}