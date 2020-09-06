package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Favorite;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.repository.FavoritesRepository;

import java.util.Date;
import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService{

    @Autowired
    FavoritesRepository favoritesRepository;
    @Autowired
    private AppUserRepository userRepository;

    //not used
    public List<Favorite> getAllFavorites(){
        return favoritesRepository.findAll();
    }
//not used
    public void addFavorite(Favorite favorite) {
        favoritesRepository.save(favorite);

    }
    @Override
    public Favorite findById(long id) {

        return favoritesRepository.findById(id);
    }

    /**
     * creates a new Favorite object and saves to repository
     * @param place place added to favorites
     * @param user user that added place to favorites
     */
    @Override
    public void addToFavorites (Place place, AppUser user){
     Favorite addMe = new Favorite();
     addMe.setPlace(place);
     addMe.setUser(user);
     addMe.setDateAdded(new Date());
     favoritesRepository.save(addMe);
    }

    /**
     * deletes an existing Favorite object
     * @param place place added to favorites
     * @param user user that added place to favorites
     */
    @Override
    public void removeFavorite (Place place, AppUser user){
      Favorite removeMe = favoritesRepository.findByPlaceAndUser(place,user);
      favoritesRepository.delete(removeMe);
    }

    /**
     * finds all the favorite objects where a place was added
     * @param place place added to favorites
     * @return list of favorite objects
     */
    @Override
    public List<Favorite> getFavsForPlace(Place place){
        return favoritesRepository.findByPlace(place);
    }

    public List<Favorite> findByUser(long id) {

        return favoritesRepository.findByUser(userRepository.findById(id));
    }

    /**
     * finds how many time a place has been added to favorites
     * @param place place to check
     * @return
     */
    @Override
    public int placePopularity (Place place) { return getFavsForPlace(place).size();}

    /**
     * checks if current user already added a place to favorites
     * @param place place to check if added
     * @param user current user
     * @return true if current user has added place to favorites, false if the place is not added; also returns false if user is null
     */
    @Override
    public boolean isAdded(Place place, AppUser user){
        if(user == null) return false;
        else
          return favoritesRepository.findByPlaceAndUser(place, user) != null;
    }
    //not used
    public boolean isAdded2(Place place){
        return favoritesRepository.findByPlace(place).size() > 0;
    }

}
