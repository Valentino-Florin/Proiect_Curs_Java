package sci.travel_app.walkthebear.service;

import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating findById(long id);

    Rating create(Rating rating, Place place, AppUser user);

    void updateRating(long id);

    void deleteRating(long id);
    boolean isAdded(Place place, AppUser user);

    List<Rating> findAll();

    List<Rating> getAllRatingsOfPlace(Place place);

    List<Rating> getAllRatingsOfPlaceById(long id);

}

