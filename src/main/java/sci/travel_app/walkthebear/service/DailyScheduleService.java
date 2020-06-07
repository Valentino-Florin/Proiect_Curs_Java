package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.DailySchedule;
import sci.travel_app.walkthebear.model.entities.Itinerary;
import sci.travel_app.walkthebear.repository.DailyScheduleRepository;

import java.util.List;

@Service
public class DailyScheduleService {
    @Autowired
    private DailyScheduleRepository scheduleRepository;

    public DailySchedule getDay(long id){
        return scheduleRepository.findById(id);
    }

    public List<DailySchedule> getAllDays(Itinerary itinerary){
        return scheduleRepository.findByItinerary(itinerary);
    }

    public int getNumberOfDays(Itinerary itinerary){
        List<DailySchedule> allDays = scheduleRepository.findByItinerary(itinerary);
         return allDays.size();
    }
//    public String dayName(Itinerary itinerary){
//        String nameA = "Day " + (getNumberOfDays(itinerary));
//        while (scheduleRepository.findByNameAndItinerary(nameA, itinerary) != null){
//
//        }
//    }

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
        scheduleRepository.deleteInBatch(toBeDeleted);
//        for (DailySchedule axed : toBeDeleted){
//            scheduleRepository.delete(axed);
//        }
    }

}
