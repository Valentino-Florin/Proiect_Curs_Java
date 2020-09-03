package sci.travel_app.walkthebear.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.repository.FavoritesRepository;
import sci.travel_app.walkthebear.repository.PlacesRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PlacesServiceImp implements PlacesService {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PlacesServiceImp .class);
    @Autowired
    private PlacesRepository placesRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private FavoritesRepository favoritesRepository;

    /**
     *
     * @param placeId
     * @return
     */
    @Override
    public Place getPlaceById(long placeId) {
        return placesRepository.findById(placeId);
    }

    /**
     *
     * @param placeId
     * @param user
     * @return
     */
    @Override
    public Place getUserPlaceById(long placeId, AppUser user) {
        Place place = placesRepository.findById(placeId);
        place.setUser(user);
        return place;
    }

    /**
     *
     * @param placeName
     * @return
     */
    @Override
    public List<Place>  getPlaceByName(String placeName) {
        List<Place> list = new ArrayList<>();
        placesRepository.findByName(placeName).forEach(list::add);
        return list;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Place> getAllPlaces() {
        List<Place> list = new ArrayList<>();
        placesRepository.findAll().forEach(list::add);
        return list;
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public List<Place> getAllUserPlaces(AppUser user) {
        List<Place> list = new ArrayList<>();
        placesRepository.findAll().forEach(list::add);
        return list;
    }

    /**
     *
     * @param category
     * @return
     */
    @Override
    public List<Place> getPlaceByCategory(Category category) {
        List<Place> list = new ArrayList<>();
        placesRepository.findByCategory(category).forEach(list::add);
        return list;
    }

    /**
     *
     * @param place
     * @return
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
     *
     * @param place
     * @param user
     */
    @Override
    public void addUserPlace(Place place, AppUser user) {
        place.setUser(user);
        List<Place> list = (List<Place>) placesRepository.findByName(place.getName());
        if (list.size() > 0) {
            logger.log(Level.ERROR, "this place is already added ");
        } else {
            placesRepository.save(place);
        }
    }

    /**
     *
     * @param place
     */
    @Override
    public void updatePlace(Place place) {
        placesRepository.save(place);
    }

    /**
     *
     * @param place
     * @param user
     */
    @Override
    public void updateUserPlace(Place place, AppUser user) {
        place.setUser(user);
        placesRepository.save(place);
    }

    /**
     *
     * @param placeId
     */
    @Override
    public void deletePlace(long placeId) {

        placesRepository.delete(getPlaceById(placeId));
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public List<Place> findPlaceByUser(AppUser user) {
        return placesRepository.findPlaceByUser(user);
    }

    public void updatePhotos (Place place, MultipartFile multipartFile, MultipartFile[] galleryImageFiles) throws IOException {
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
        placeBis.setThumbnailFileName(place.getThumbnailFileName());

        String fileNameT = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        placeBis.setThumbnailFileName(fileNameT);
        int count = 0;
        for (MultipartFile galleryImage : galleryImageFiles) {
            if (galleryImage != null){
                String fileNameG = StringUtils.cleanPath(Objects.requireNonNull(galleryImage.getOriginalFilename()));
                if (count == 0) {placeBis.setGalleryImage1FileName(fileNameG);}
                else if (count == 1) {placeBis.setGalleryImage2FileName(fileNameG);}
                else if (count == 2) {placeBis.setGalleryImage3FileName(fileNameG);}
                else if (count == 3) {placeBis.setGalleryImage4FileName(fileNameG);}
                else if (count == 4) {placeBis.setGalleryImage5FileName(fileNameG);}

                uploadService.uploadImageFile(place, galleryImage, fileNameG);
            }
            else {
                if (count == 0) {placeBis.setGalleryImage1FileName(place.getGalleryImage1FileName());}
                else if (count == 1) {placeBis.setGalleryImage2FileName(place.getGalleryImage2FileName());}
                else if (count == 2) {placeBis.setGalleryImage3FileName(place.getGalleryImage3FileName());}
                else if (count == 3) {placeBis.setGalleryImage4FileName(place.getGalleryImage4FileName());}
                else if (count == 4) {placeBis.setGalleryImage5FileName(place.getGalleryImage5FileName());}
            }
            count++;
        }
        placesRepository.save(placeBis);
    }

    /**
     *
     * @param pageable
     * @param category
     * @return
     */
    @Override
    public Page<Place> getPaginatedPlaceList(Pageable pageable, Category category) {

        return placesRepository.findByCategory(category, pageable);
    }

    /**
     *
     * @param pageNum
     * @param sortField
     * @param sortDir
     * @param category
     * @return
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
     *
     * @param pageNum
     * @param sortField
     * @param sortDir
     * @param keyword
     * @return
     */
    @Override
    public Page<Place> getPaginatedPlaceListByKeyword(int pageNum, String sortField, String sortDir, String keyword){
        Pageable pageable = PageRequest.of(pageNum - 1, 5,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        return placesRepository.findByNameContains(keyword,pageable);
    }

    /**
     *
     * @return
     */
    @Override
    public List <Place> latestPlaces(){
        return placesRepository.findAllByOrderByCreatedDesc();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Place> mostPopularPlaces() {
        List<Long> placeId = favoritesRepository.getPlacesFromFav();
        List<Place> popularityClub = new ArrayList<>();
        for (long id : placeId){
            popularityClub.add(placesRepository.findById(id));
        }
        return popularityClub;
    }

}
