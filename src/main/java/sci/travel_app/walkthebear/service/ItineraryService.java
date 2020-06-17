package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Itinerary;
import sci.travel_app.walkthebear.repository.ItineraryRepository;

import java.util.Date;
import java.util.List;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository tripRepository;

    //not used
    public Itinerary createItinerary(String name) {
        Itinerary itinerary = new Itinerary(name);
        itinerary.setCreated(new Date());
        return tripRepository.save(itinerary);
    }

    /**
     * takes a new itinerary object, adds some values and saves to repository
     * @param itinerary itinerary object to save to repository
     * @param user current user
     * @return itinerary object with updated fields
     */
    public Itinerary saveItinerary(Itinerary itinerary, AppUser user) {
        itinerary.setCreated(new Date());
        itinerary.setUser(user);
        tripRepository.save(itinerary);
        return itinerary;
    }

    /**
     * creates a new object and passes all the values from the old object, then saves the new object in the repository
     * @param itinerary itinerary object to update
     * @param id id of itinerary object to update
     * @param user  logged in user
     */
    public void update(Itinerary itinerary, long id, AppUser user){
        Itinerary saveMe = new Itinerary();
        saveMe.setItineraryId(id);
        saveMe.setUser(user);
        saveMe.setName(itinerary.getName());
        saveMe.setDescription(itinerary.getDescription());
        saveMe.setCreated(itinerary.getCreated());
        saveMe.setStartDate(itinerary.getStartDate());
        tripRepository.save(saveMe);
    }

    /**
     * deletes an Itinerary
     * @param itinerary itinerary object to delete
     */
    public void deleteItinerary(Itinerary itinerary) {
        tripRepository.delete(itinerary);
    }

    /**
     * finds an itinerary object using an id
     * @param id itinerary id
     * @return returns itinerary object with corresponding id
     */
    public Itinerary findById(long id) {
        return tripRepository.findById(id);
    }

    /**
     * finds all itinerary objects created by one user
     * @param user user that created the itineraries
     * @return list of itinerary objects
     */
    public List<Itinerary> findByUser(AppUser user) {
        return tripRepository.findByUser(user);
    }

    /**
     * finds all itinerary objects in database
     * @return list of itinerary objects
     */
    public List<Itinerary> findAll() {
        return tripRepository.findAll();
    }


}

