package sci.travel_app.WalkTheBear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.repository.DailyScheduleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DailyScheduleService {
    @Autowired
    private DailyScheduleRepository scheduleRepository;

    public DailySchedule getDay(long id){
        DailySchedule day = scheduleRepository.findById(id);
        return day;
    }

    public List<DailySchedule> getAllDays(Itinerary itinerary){
        return scheduleRepository.findByItinerary(itinerary);
    }

    public int getNumberOfDays(Itinerary itinerary){
        List<DailySchedule> AllDays = scheduleRepository.findByItinerary(itinerary);
        int numberOfDays = AllDays.size();
         return numberOfDays;
    }

    public DailySchedule addNewDay(Itinerary itinerary) {
        DailySchedule timetable = new DailySchedule();
        timetable.setItinerary(itinerary);
        timetable.setName("Day " + (getNumberOfDays(itinerary) + 1));
        return scheduleRepository.save(timetable);
    }

    public void saveDay(DailySchedule dailySchedule){
            scheduleRepository.save(dailySchedule);
    }

    public void removeDay(DailySchedule dailySchedule){
        scheduleRepository.delete(dailySchedule);
    }
    public void deleteAll(Itinerary itinerary){
        List<DailySchedule> toBeDeleted = getAllDays(itinerary);
        for (DailySchedule axed : toBeDeleted){
            scheduleRepository.delete(axed);
        }
    }

}
