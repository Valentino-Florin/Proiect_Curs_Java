package sci.travel_app.walkthebear.service;

import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;

public interface RatingService {

    public Rating findById(long id);

    public Rating create(Rating rating, Place place);



}

