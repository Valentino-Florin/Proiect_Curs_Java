package sci.travel_app.walkthebear.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.data_utils.UploadService;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.repository.FavoritesRepository;
import sci.travel_app.walkthebear.repository.PlacesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlacesServiceImp implements PlacesService {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PlacesServiceImp.class);
    @Autowired
    private PlacesRepository placesRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private FavoritesRepository favoritesRepository;

    /**
     * Method searches for a place by ID
     *
     * @param placeId- unique ID used to identify the place
     * @return - the place specific for the ID
     */
    @Override
    public Place getPlaceById(long placeId) {
        return placesRepository.findById(placeId);
    }

    /**
     * Method searches for a place by ID that is added by a specific user
     *
     * @param placeId - unique ID used to identify the place
     * @param user    - user that added the place
     * @return - place that was found
     */
    @Override
    public Place getUserPlaceById(long placeId, AppUser user) {
        Place place = placesRepository.findById(placeId);
        place.setUser(user);
        return place;
    }

    /**
     * List of places with a certain name
     *
     * @param placeName - place name keyword
     * @return - list of places by name
     */
    @Override
    public List<Place> getPlaceByName(String placeName) {
        List<Place> list = new ArrayList<>();
        placesRepository.findByName(placeName).forEach(list::add);
        return list;
    }

    /**
     * List of all the places in the data base
     *
     * @return all places in the data base
     */
    @Override
    public List<Place> getAllPlaces() {
        List<Place> list = new ArrayList<>();
        placesRepository.findAll().forEach(list::add);
        return list;
    }

    /**
     * Creates a list of all places added by a user
     *
     * @param user- user that added the places
     * @return List of all places added by a user
     */
    @Override
    public List<Place> getAllUserPlaces(AppUser user) {
        List<Place> list = new ArrayList<>();
        placesRepository.findAll().forEach(list::add);
        return list;
    }

    /**
     * Creates a list of all places specific to a category
     *
     * @param category - place category
     * @return List of places by category
     */
    @Override
    public List<Place> getPlaceByCategory(Category category) {
        List<Place> list = new ArrayList<>();
        placesRepository.findByCategory(category).forEach(list::add);
        return list;
    }

    /**
     * Searches if a new place is already added and if not it saves it
     *
     * @param place - place object
     */
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

    /**
     * Saves a new place added by a specific user.
     *
     * @param place - place object
     * @param user  - user that added the place
     */
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

    /**
     *  Updates the place with new details
     * @param place - place object
     * @param id i unique ID for place
     */

    @Override
    public void updatePlace(Place place, long id) {
        Place savePlace = new Place();
        savePlace.setUser(place.getUser());
        savePlace.setId(place.getId());
        savePlace.setName(place.getName());
        savePlace.setCounty(place.getCounty());
        savePlace.setCity(place.getCity());
        savePlace.setAddress(place.getAddress());
        savePlace.setCoordinates(place.getCoordinates());
        savePlace.setPhoneNumber(place.getPhoneNumber());
        savePlace.setEmail(place.getEmail());
        savePlace.setCategory(place.getCategory());
        savePlace.setSubcategory(place.getSubcategory());
        savePlace.setWorkingHours(place.getWorkingHours());
        savePlace.setDescription(place.getDescription());
        savePlace.setCreated(place.getCreated());
        savePlace.setThumbnailFileName(placesRepository.findById(id).getThumbnailFileName());
        savePlace.setGalleryImage1FileName(placesRepository.findById(id).getThumbnailFileName());
        savePlace.setGalleryImage2FileName(placesRepository.findById(id).getGalleryImage1FileName());
        savePlace.setGalleryImage3FileName(placesRepository.findById(id).getGalleryImage2FileName());
        savePlace.setGalleryImage4FileName(placesRepository.findById(id).getGalleryImage3FileName());
        savePlace.setGalleryImage5FileName(placesRepository.findById(id).getGalleryImage4FileName());
        savePlace.setGalleryImage5FileName(placesRepository.findById(id).getGalleryImage5FileName());

        placesRepository.save(savePlace);
    }

    /**
     * Updates the place and place pictures added by an specific user
     *
     * @param place     - place object
     * @param user      - user that added the place
     */
    @Override
    public void updateUserPlace(Place place, AppUser user, long id) {
        Place saveMe = new Place();
        saveMe.setUser(user);
        saveMe.setId(place.getId());
        saveMe.setName(place.getName());
        saveMe.setCounty(place.getCounty());
        saveMe.setCity(place.getCity());
        saveMe.setAddress(place.getAddress());
        saveMe.setCoordinates(place.getCoordinates());
        saveMe.setPhoneNumber(place.getPhoneNumber());
        saveMe.setEmail(place.getEmail());
        saveMe.setCategory(place.getCategory());
        saveMe.setSubcategory(place.getSubcategory());
        saveMe.setWorkingHours(place.getWorkingHours());
        saveMe.setDescription(place.getDescription());
        saveMe.setCreated(place.getCreated());
        saveMe.setThumbnailFileName(placesRepository.findById(id).getThumbnailFileName());
        saveMe.setGalleryImage1FileName(placesRepository.findById(id).getThumbnailFileName());
        saveMe.setGalleryImage2FileName(placesRepository.findById(id).getGalleryImage1FileName());
        saveMe.setGalleryImage3FileName(placesRepository.findById(id).getGalleryImage2FileName());
        saveMe.setGalleryImage4FileName(placesRepository.findById(id).getGalleryImage3FileName());
        saveMe.setGalleryImage5FileName(placesRepository.findById(id).getGalleryImage4FileName());
        saveMe.setGalleryImage5FileName(placesRepository.findById(id).getGalleryImage5FileName());

        placesRepository.save(saveMe);
    }

    /**
     * Finds a place object by ID and delets it
     *
     * @param placeId - unique ID specific to a place.
     */
    @Override
    public void deletePlace(long placeId) {

        placesRepository.delete(getPlaceById(placeId));
    }

    /**
     * Finds all the places specific to a user
     *
     * @param user - user that added the place
     * @return - list of  places added by the user
     */
    @Override
    public List<Place> findPlaceByUser(AppUser user) {
        return placesRepository.findPlaceByUser(user);
    }

    /**
     * Updates the photos specific to a place
     *
     * @param place     - place object
     * @param thumbnail - thumbnail path
     * @param gallery1  - picture1 path
     * @param gallery2  - picture2 path
     * @param gallery3  - picture3 path
     * @param gallery4  - picture4 path
     * @param gallery5  - picture5 path
     */

    @Override
    public void updatePhotos(Place place, String thumbnail, String gallery1, String gallery2, String gallery3, String gallery4, String gallery5) {
        Place placeBis = new Place();
        placeBis.setId(place.getId());
        placeBis.setName(place.getName());
        placeBis.setCounty(place.getCounty());
        placeBis.setCity(place.getCity());
        placeBis.setAddress(place.getAddress());
        placeBis.setCoordinates(place.getCoordinates());
        placeBis.setPhoneNumber(place.getPhoneNumber());
        placeBis.setEmail(place.getEmail());
        placeBis.setCategory(place.getCategory());
        placeBis.setSubcategory(place.getSubcategory());
        placeBis.setWorkingHours(place.getWorkingHours());
        placeBis.setDescription(place.getDescription());
        placeBis.setCreated(place.getCreated());
        placeBis.setUser(place.getUser());
        if (!"".equals(thumbnail)) {
            placeBis.setThumbnailFileName(thumbnail);
        } else {
            placeBis.setThumbnailFileName(place.getThumbnailFileName());
        }
        if (!"".equals(gallery1)) {
            placeBis.setGalleryImage1FileName(gallery1);
        } else {
            placeBis.setGalleryImage1FileName(place.getGalleryImage1FileName());
        }
        if (!"".equals(gallery2)) {
            placeBis.setGalleryImage2FileName(gallery2);
        } else {
            placeBis.setGalleryImage2FileName(place.getGalleryImage2FileName());
        }
        if (!"".equals(gallery3)) {
            placeBis.setGalleryImage3FileName(gallery3);
        } else {
            placeBis.setGalleryImage3FileName(place.getGalleryImage3FileName());
        }
        if (!"".equals(gallery4)) {
            placeBis.setGalleryImage4FileName(gallery4);
        } else {
            placeBis.setGalleryImage4FileName(place.getGalleryImage4FileName());
        }
        if (!"".equals(gallery5)) {
            placeBis.setGalleryImage5FileName(gallery5);
        } else {
            placeBis.setGalleryImage5FileName(place.getGalleryImage5FileName());
        }

        placesRepository.save(placeBis);
    }

    /**
     * Checks if an image is already added.
     *
     * @param s
     * @return - tru or false depending on the result
     */
    @Override
    public Boolean hasPic(String s) {
        if (!"".equals(s)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Implements pagination for place category
     *
     * @param category - place category
     * @return places by category
     */
    @Override
    public Page<Place> getPaginatedPlaceList(Pageable pageable, Category category) {

        return placesRepository.findByCategory(category, pageable);
    }

    /**
     * Implements pagination for place list category
     *
     * @param pageNum   - page number
     * @param sortField - stores information on how to sort           -
     * @param sortDir
     * @param category  - place category
     * @return places by category
     */
    @Override
    public Page<Place> getPaginatedPlaceListByCategory(int pageNum, String sortField, String sortDir, Category category) {

        Pageable pageable = PageRequest.of(pageNum - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        return placesRepository.findByCategory(category, pageable);
    }

    /**
     * Implements pagination for keyword search
     *
     * @param pageNum   - page number
     * @param sortField - stores information on how to sort
     * @param sortDir
     * @param keyword   - keyword used for search
     * @return - search results for the keyword
     */
    @Override
    public Page<Place> getPaginatedPlaceListByKeyword(int pageNum, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNum - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        return placesRepository.findByNameContains(keyword, pageable);
    }

    /**
     * Find the last places saved
     *
     * @return the latest places added
     */
    @Override
    public List<Place> latestPlaces() {
        return placesRepository.findAllByOrderByCreatedDesc();
    }

    /**
     * Find place by keyword
     *
     * @param keyword - keyword used for search
     * @return - all places that have the keyword in the name
     */
    @Override
    public List<Place> findByKeyword(String keyword) {
        return placesRepository.findByKeyword(keyword);
    }

    /**
     * Finds the most popular places by checking the users favorite
     *
     * @return -      most popular places
     */
    @Override
    public List<Place> mostPopularPlaces() {
        List<Long> placeId = favoritesRepository.getPlacesFromFav();
        List<Place> popularityClub = new ArrayList<>();
        for (long id : placeId) {
            popularityClub.add(placesRepository.findById(id));
        }
        return popularityClub;
    }


}
