package sci.travel_app.WalkTheBear.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.misc.Category;
import sci.travel_app.WalkTheBear.repository.PlacesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlacesServiceImp implements PlacesService {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PlacesServiceImp .class);
    @Autowired
    private PlacesRepository placesRepository;


    @Override
    public Place getPlaceById(long placeId) {
        Place place = placesRepository.findById(placeId);
        return place;
    }

    @Override
    public List<Place>  getPlaceByName(String placeName) {
        List<Place> list = new ArrayList<>();
        placesRepository.findByName(placeName).forEach(list::add);
        return list;
    }

    @Override
    public List<Place> getAllPlaces() {
        List<Place> list = new ArrayList<>();
        placesRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public List<Place> getPlaceByCategory(Category category) {
        List<Place> list = new ArrayList<>();
        placesRepository.findByCategory(category).forEach(list::add);
        return list;
    }

    @Override
    public void addPlace(Place place) {
        List<Place> list = placesRepository.findByName(place.getName());
            if (list.size() > 0) {
                logger.log(Level.ERROR, "this place is already added ");
            } else {
                placesRepository.save(place);
            }
    }
    @Override
    public void updatePlace(Place place) {
        placesRepository.save(place);
    }

    @Override
    public void deletePlace(long placeId) {
        placesRepository.delete(getPlaceById(placeId));
    }

    @Override
    public Page<Place> getPaginatedPlaceList(Pageable pageable) {
        return placesRepository.findAll(pageable);
    }


}
