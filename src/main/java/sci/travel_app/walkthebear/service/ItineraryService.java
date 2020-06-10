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

    public Itinerary createItinerary(String name) {
        Itinerary itinerary = new Itinerary(name);
        itinerary.setCreated(new Date());
        return tripRepository.save(itinerary);
    }
public void update(Itinerary itinerary, long id){
    Itinerary saveMe = new Itinerary();
    saveMe.setItineraryId(id);
    saveMe.setUser(itinerary.getUser());
    saveMe.setName(itinerary.getName());
    saveMe.setDescription(itinerary.getDescription());
    saveMe.setCreated(itinerary.getCreated());
    saveMe.setStartDate(itinerary.getStartDate());
        tripRepository.save(saveMe);
}

    public Itinerary saveItinerary(Itinerary itinerary, AppUser user) {
            itinerary.setCreated(new Date());
            itinerary.setUser(user);
            tripRepository.save(itinerary);
        return itinerary;
    }



    public void deleteItinerary(Itinerary itinerary) {
        tripRepository.delete(itinerary);
    }


    public Itinerary findById(long id) {
        return tripRepository.findById(id);
    }

    public List<Itinerary> findByUser(AppUser user) {
        return tripRepository.findByUser(user);
    }

    public List<Itinerary> findAll() {
        return tripRepository.findAll();
    }


}

