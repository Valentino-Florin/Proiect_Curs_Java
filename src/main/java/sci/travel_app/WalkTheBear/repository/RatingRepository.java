package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.entities.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByPlace(Place ratedPlace);
   List<Rating> findByUser(AppUser user);
  List<Rating> findByUserAndPlace(AppUser user, long id);

    Rating findById(long id);

}