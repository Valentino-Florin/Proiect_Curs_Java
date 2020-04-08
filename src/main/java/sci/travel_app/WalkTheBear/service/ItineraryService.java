package sci.travel_app.WalkTheBear.service;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.repository.ItineraryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItineraryService {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(ItineraryService.class);

    @Autowired
    private ItineraryRepository tripRepository;

    //    public List<Itinerary>
    public List<DailySchedule> Schedule = new ArrayList<>();
    public List<Place> unplannedPlaces = new ArrayList<>();


    public Itinerary createItinerary() {
        Itinerary itinerary = new Itinerary();
        List<DailySchedule> Schedule = new ArrayList<>();
        DailySchedule defaultDay = new DailySchedule();
        defaultDay.day.put("00:00", null);
        defaultDay.day.put("01:00", null);
        defaultDay.day.put("02:00", null);
        defaultDay.day.put("03:00", null);
        defaultDay.day.put("04:00", null);
        defaultDay.day.put("05:00", null);
        defaultDay.day.put("07:00", null);
        defaultDay.day.put("08:00", null);
        defaultDay.day.put("09:00", null);
        defaultDay.day.put("10:00", null);
        defaultDay.day.put("11:00", null);
        defaultDay.day.put("12:00", null);
        defaultDay.day.put("13:00", null);
        defaultDay.day.put("14:00", null);
        defaultDay.day.put("15:00", null);
        defaultDay.day.put("16:00", null);
        defaultDay.day.put("17:00", null);
        defaultDay.day.put("18:00", null);
        defaultDay.day.put("19:00", null);
        defaultDay.day.put("20:00", null);
        defaultDay.day.put("21:00", null);
        defaultDay.day.put("22:00", null);
        defaultDay.day.put("23:00", null);
        Schedule.add(defaultDay);
        return tripRepository.save(itinerary);
    }

    public Itinerary saveItinerary(Itinerary itinerary) {
        return tripRepository.save(itinerary);
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

