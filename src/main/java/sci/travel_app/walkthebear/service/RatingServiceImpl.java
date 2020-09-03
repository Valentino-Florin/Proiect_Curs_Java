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
     *
     * @return
     */
    @Override
    public List<Rating> findAll() {

        return ratingRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Rating findById(long id) {

        return ratingRepository.findById(id);
    }

    /**
     *
     * @param rating
     * @param place
     * @param user
     * @return
     */
    @Override
    public Rating create(Rating rating, Place place, AppUser user) {
        rating.setUser(user);
        rating.setCreated(new Date());
        rating.setPlace(place);

        return ratingRepository.save(rating);
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteRating(long id) {
        ratingRepository.delete(findById(id));
    }

    /**
     *
     * @param id
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

    public List<Rating> findByUser(long id) {

        return ratingRepository.findByUser(userRepository.findById(id));
    }

}
