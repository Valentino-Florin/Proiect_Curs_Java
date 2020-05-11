package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.repository.PlacesRepository;
import sci.travel_app.walkthebear.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private PlacesRepository placesRepository;

    public List<Rating> getAllRated(){
        List<Rating> rated = new ArrayList<>();
        ratingRepository.findAll().forEach(rated::add);
        return rated;
    }

    @Override
    public Rating findById(long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRatingsOfPlace(Place place){
        return ratingRepository.findByPlace(place);
    }
    public List<Rating> getAllRatingsOfPlaceById(long id){
        return ratingRepository.findByPlace(placesRepository.findById(id));
    }


}
