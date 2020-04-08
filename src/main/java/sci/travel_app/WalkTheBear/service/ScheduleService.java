package sci.travel_app.WalkTheBear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.repository.DailyScheduleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private DailyScheduleRepository ScheduleRepository;

    public List<Place> unplannedPlaces = new ArrayList<>();

    public List<DailySchedule> Schedule = new ArrayList<>();
    public int numberOfDays = Schedule.size();
    public String nameOfDay = "Day " + String.valueOf(numberOfDays);
    public void addNewDay() {
        DailySchedule timetable = new DailySchedule(nameOfDay);
        timetable.day.put("00:00", null);
        timetable.day.put("01:00", null);
        timetable.day.put("02:00", null);
        timetable.day.put("03:00", null);
        timetable.day.put("04:00", null);
        timetable.day.put("05:00", null);
        timetable.day.put("07:00", null);
        timetable.day.put("08:00", null);
        timetable.day.put("09:00", null);
        timetable.day.put("10:00", null);
        timetable.day.put("11:00", null);
        timetable.day.put("12:00", null);
        timetable.day.put("13:00", null);
        timetable.day.put("14:00", null);
        timetable.day.put("15:00", null);
        timetable.day.put("16:00", null);
        timetable.day.put("17:00", null);
        timetable.day.put("18:00", null);
        timetable.day.put("19:00", null);
        timetable.day.put("20:00", null);
        timetable.day.put("21:00", null);
        timetable.day.put("22:00", null);
        timetable.day.put("23:00", null);
    }
    public void removeDay(){

    }
    public void addToSchedule(Place place, DailySchedule timetable, String time){
//pair it up with the time and add to map
        boolean isKeyPresent = timetable.day.containsKey(time);
       if (isKeyPresent){
           timetable.day.put(time,place);
       }
       //remove from list
        unplannedPlaces.remove(place);
    }
    public void removeFromSchedule(Place place, DailySchedule timetable, String time){
        
    }

}
