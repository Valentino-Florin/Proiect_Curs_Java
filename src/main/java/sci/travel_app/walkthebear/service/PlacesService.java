package sci.travel_app.walkthebear.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;

import java.util.List;

public interface PlacesService {
    Place getPlaceById(long placeId);
    List<Place>  getPlaceByName(String name);
    List<Place> getAllPlaces();
    List<Place> getPlaceByCategory(Category category);
    void addPlace(Place place);
    void updatePlace(Place place);
    void deletePlace(long placeId);
    Page<Place> getPaginatedPlaceList(Pageable pageable);
}
