package sci.travel_app.WalkTheBear.service;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.repository.ItineraryRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItineraryService {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(ItineraryService.class);

    @Autowired
    private ItineraryRepository tripRepository;


    public List<Place> unplannedPlaces = new ArrayList<>();


    public Itinerary createItinerary(String name) {
        Itinerary itinerary = new Itinerary(name);
        itinerary.setCreated(new Date());
        return tripRepository.save(itinerary);
    }
public void update(Itinerary itinerary){
    tripRepository.save(itinerary);
}

    public Itinerary saveItinerary(Itinerary itinerary) {
            itinerary.setCreated(new Date());
            tripRepository.save(itinerary);
        return itinerary;
    }



    public void deleteItinerary(Itinerary itinerary) {
        tripRepository.delete(itinerary);
    }


    public Itinerary findById(long id) {
        Itinerary itinerary = tripRepository.findById(id);
        return itinerary;
    }

    public List<Itinerary> findByUser(AppUser user) {
        List<Itinerary> itinerary = tripRepository.findByUser(user);
        return itinerary;
    }

    public List<Itinerary> findAll() {
        List<Itinerary> itinerary = tripRepository.findAll();
        return itinerary;
    }


}

