package sci.travel_app.WalkTheBear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.repository.DailyScheduleRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleService {
    @Autowired
    private DailyScheduleRepository ScheduleRepository;

    public List<Place> unplannedPlaces = new ArrayList<>();


    public int getNumberOfDays(Itinerary itinerary){
        List<DailySchedule> AllDays = ScheduleRepository.findByItinerary(itinerary);
        int numberOfDays = AllDays.size();
         return numberOfDays;
    }
public void populateDefaults(DailySchedule dailySchedule){
    Map<String, Place> daySchedule = new HashMap<>();
    daySchedule.put("00:00", null);
    daySchedule.put("01:00", null);
    daySchedule.put("02:00", null);
    daySchedule.put("03:00", null);
    daySchedule.put("04:00", null);
    daySchedule.put("05:00", null);
    daySchedule.put("07:00", null);
    daySchedule.put("08:00", null);
    daySchedule.put("09:00", null);
    daySchedule.put("10:00", null);
    daySchedule.put("11:00", null);
    daySchedule.put("12:00", null);
    daySchedule.put("13:00", null);
    daySchedule.put("14:00", null);
    daySchedule.put("15:00", null);
    daySchedule.put("16:00", null);
    daySchedule.put("17:00", null);
    daySchedule.put("18:00", null);
    daySchedule.put("19:00", null);
    daySchedule.put("20:00", null);
    daySchedule.put("21:00", null);
    daySchedule.put("22:00", null);
    daySchedule.put("23:00", null);
    dailySchedule.setDay(daySchedule);
}
    public DailySchedule addNewDay(Itinerary itinerary) {
        DailySchedule timetable = new DailySchedule();
        timetable.setName("Day " + (getNumberOfDays(itinerary) + 1));
//        Map<String, Place> daySchedule = new HashMap<>();
//        daySchedule.put("00:00", null);
//        daySchedule.put("01:00", null);
//        daySchedule.put("02:00", null);
//        daySchedule.put("03:00", null);
//        daySchedule.put("04:00", null);
//        daySchedule.put("05:00", null);
//        daySchedule.put("07:00", null);
//        daySchedule.put("08:00", null);
//        daySchedule.put("09:00", null);
//        daySchedule.put("10:00", null);
//        daySchedule.put("11:00", null);
//        daySchedule.put("12:00", null);
//        daySchedule.put("13:00", null);
//        daySchedule.put("14:00", null);
//        daySchedule.put("15:00", null);
//        daySchedule.put("16:00", null);
//        daySchedule.put("17:00", null);
//        daySchedule.put("18:00", null);
//        daySchedule.put("19:00", null);
//        daySchedule.put("20:00", null);
//        daySchedule.put("21:00", null);
//        daySchedule.put("22:00", null);
//        daySchedule.put("23:00", null);
//        timetable.setDay(daySchedule);
        populateDefaults(timetable);
        return ScheduleRepository.save(timetable);
    }
    public void removeDay(){

    }

//    public void addToSchedule(Place place, DailySchedule timetable, String time){
////pair it up with the time and add to map
//        boolean isKeyPresent = (timetable.getDay()).containsKey(time);
//       if (isKeyPresent){
//           timetable.day.put(time,place);
//       }
//       //remove from list
//// unplannedPlaces.remove(place);
//    }
    public void removeFromSchedule(Place place, DailySchedule timetable, String time){
        
    }

}
