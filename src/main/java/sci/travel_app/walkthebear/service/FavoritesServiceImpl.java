package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.Favorite;
import sci.travel_app.walkthebear.repository.FavoritesRepository;

import java.util.ArrayList;
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

}
