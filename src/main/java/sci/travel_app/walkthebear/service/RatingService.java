package sci.travel_app.walkthebear.service;

import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;

public interface RatingService {

    Rating findById(long id);

    Rating create(Rating rating, Place place);

    void updateRating(long id);

    void deleteRating(long id);

}

