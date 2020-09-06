package sci.travel_app.walkthebear.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.repository.PlacesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlacesServiceImp implements PlacesService {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PlacesServiceImp .class);
    @Autowired
    private PlacesRepository placesRepository;


    @Override
    public Place getPlaceById(long placeId) {
        return placesRepository.findById(placeId);
    }

    @Override
    public Place getUserPlaceById(long placeId, AppUser user) {
        Place place = placesRepository.findById(placeId);
        place.setUser(user);
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
    public List<Place> getAllUserPlaces(AppUser user) {
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
    public Place addPlace(Place place) {
        List<Place> list = (List<Place>) placesRepository.findByName(place.getName());
        if (list.size() > 0) {
            logger.log(Level.ERROR, "this place is already added ");
        } else {
            placesRepository.save(place);
        }
        return place;
    }
    @Override
    public Place addUserPlace(Place place, AppUser user) {
        place.setUser(user);
        List<Place> list = (List<Place>) placesRepository.findByName(place.getName());
        if (list.size() > 0) {
            logger.log(Level.ERROR, "this place is already added ");
        } else {
            placesRepository.save(place);
        }
        return place;
    }
    @Override
    public void updatePlace(Place place) {
        placesRepository.save(place);
    }

    @Override
    public void updateUserPlace(Place place, AppUser user) {
        place.setUser(user);
        placesRepository.save(place);
    }

    @Override
    public void deletePlace(long placeId) {

        placesRepository.delete(getPlaceById(placeId));
    }
    @Override
    public List<Place> findPlaceByUser(AppUser user) {
        return placesRepository.findPlaceByUser(user);
    }

    @Override
    public Page<Place> getPaginatedPlaceList(Pageable pageable, Category category) {

        return placesRepository.findByCategory(category, pageable);
    }
    @Override
    public Page<Place> getPaginatedPlaceListByCategory(int pageNum, String sortField, String sortDir, Category category) {

        Pageable pageable = PageRequest.of(pageNum - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        return placesRepository.findByCategory(category, pageable);
    }
    @Override
    public Page<Place> getPaginatedPlaceListByKeyword(int pageNum, String sortField, String sortDir, String keyword){
        Pageable pageable = PageRequest.of(pageNum - 1, 1,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        return placesRepository.findByNameContains(keyword,pageable);
    }

    @Override
    public List <Place> latestPlaces(){
        return placesRepository.findAllByOrderByCreatedDesc();
    }

    @Override
    public List<Place> findByKeyword(String keyword) {
        return placesRepository.findByKeyword(keyword);
    }

    //    public Page<Place> filterResults(List<Place> initiaList, boolean A, boolean B, boolean C, boolean D){
//        if(A==true && B==true && C==true && D==true){
//
//        }
//
//
//
//        Page<Place>filteredPage;
//        return  filteredPage;
//
//        }

}
