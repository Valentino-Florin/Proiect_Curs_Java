package sci.travel_app.WalkTheBear.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.entities.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findByPlace(Place ratedPlace);

    Rating findById(long id);

}