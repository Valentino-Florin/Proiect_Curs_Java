package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.repository.PlacesRepository;
import sci.travel_app.walkthebear.repository.RatingRepository;

import java.util.Date;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private PlacesRepository placesRepository;
    @Autowired
    private AppUserRepository userRepository;

    /**
     * finds all the rating objects
     * @return list of rating objects
     */
    @Override
    public List<Rating> findAll() {

        return ratingRepository.findAll();
    }

    /**
     * finds a rating object by id
     * @param id of a rating object
     * @return a rating object
     */
    @Override
    public Rating findById(long id) {

        return ratingRepository.findById(id);
    }

    /**
     * creates a new rating object
     * @param rating a new rating object
     * @param place place that is rated
     * @param user logged-in user
     * @return rating object and saves it to repository
     */
    @Override
    public Rating create(Rating rating, Place place, AppUser user) {
        rating.setUser(user);
        rating.setCreated(new Date());
        rating.setPlace(place);

        return ratingRepository.save(rating);
    }

    /**
     * deletes one rating object
     * @param id id of the rating object to be deleted
     */
    @Override
    public void deleteRating(long id) {
        ratingRepository.delete(findById(id));
    }

    @Override
    public void deleteRatingAdmin(Rating rating) {
        ratingRepository.delete(rating);
    }


    /**
     * updates an existing rating object
     * @param id id of rating object to be updated
     */
    @Override
    public void updateRating(long id) {
        ratingRepository.save(findById(id));
    }

    @Override
    public List<Rating> getAllRatingsOfPlace(Place place) {
        return ratingRepository.findByPlace(place);
    }

    @Override
    public List<Rating> getAllRatingsOfPlaceById(long id) {
        return ratingRepository.findByPlace(placesRepository.findById(id));
    }

    /**
     * finds all the rating objects from a user
     * @param id id of the logged-in user
     * @return list of rating objects
     */
    public List<Rating> findByUser(long id) {

        return ratingRepository.findByUser(userRepository.findById(id));
    }

    /**
     * checks if current user already rated a place
     * @param place place to check if added
     * @param user current user
     * @return true if logged-in user has rated the place, false if place is not rated or user is null
     */

    @Override
    public boolean isAdded(Place place, AppUser user){
        if(user == null) return false;
        else
            return ratingRepository.findByPlaceAndUser(place, user) != null;
    }

}
