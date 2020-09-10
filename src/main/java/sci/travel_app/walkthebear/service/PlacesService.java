package sci.travel_app.walkthebear.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;

import java.util.List;

public interface PlacesService {
    Place getPlaceById(long placeId);
    Place getUserPlaceById(long placeId, AppUser user);
    List<Place>  getPlaceByName(String name);
    List<Place> getAllPlaces();
    List<Place> getAllUserPlaces(AppUser user);
    public Place addUserPlace(Place place, AppUser user);
    List<Place> getPlaceByCategory(Category category);
    Place addPlace(Place place);
    void updatePlace(Place place);
    public void updateUserPlace(Place place, AppUser user, String thumbnail, String gallery1,
                                String gallery2, String gallery3, String gallery4, String gallery5);
    void deletePlace(long placeId);
    public List<Place> findPlaceByUser(AppUser user);

    Page<Place> getPaginatedPlaceList(Pageable pageable, Category category);
    Page<Place> getPaginatedPlaceListByCategory(int pageNum, String sortField, String sortDir, Category category);
    Page<Place> getPaginatedPlaceListByKeyword(int pageNum, String sortField, String sortDir, String keyword);
    List <Place> latestPlaces();
    List<Place> mostPopularPlaces();
    List <Place> findByKeyword(String keyword);
    void updatePhotos (Place place, String thumbnail, String gallery1, String gallery2, String gallery3, String gallery4, String gallery5);
    Boolean hasPic(String s);
}
