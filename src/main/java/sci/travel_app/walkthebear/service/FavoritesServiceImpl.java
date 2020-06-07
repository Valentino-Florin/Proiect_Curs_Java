package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Favorite;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.repository.FavoritesRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService{

    @Autowired
    FavoritesRepository favoritesRepository;

    public List<Favorite> getAllFavorites(){
        List<Favorite> favorites = new ArrayList<>();
        favoritesRepository.findAll().forEach(favorites::add);
        return favorites;
    }

    public void addFavorite(Favorite favorite) {
        favoritesRepository.save(favorite);

    }

    public void addToFavorites (Place place){
     Favorite addMe = new Favorite();
     addMe.setPlace(place);
//     addMe.setUser();
     addMe.setDateAdded(new Date());
     favoritesRepository.save(addMe);
    }

    public void removeFavorite (Place place, AppUser user){
      Favorite removeMe = favoritesRepository.findByPlaceAndUser(place,user);
      favoritesRepository.delete(removeMe);
    }

    public List<Favorite> getFavsForPlace(Place place){
        return favoritesRepository.findByPlace(place);
    }

    public boolean isAdded(Place place, AppUser user){
        return favoritesRepository.findByPlaceAndUser(place, user) != null;
    }

    public boolean isAdded2(Place place){
        return favoritesRepository.findByPlace(place).size() > 0;
    }

}
