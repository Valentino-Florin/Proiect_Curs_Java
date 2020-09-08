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

    /**
     * get the number of days for a certain itinerary
     * @param itinerary  itinerary to check no of days for
     * @return an int = no of days
     */
    public int getNumberOfDays(Itinerary itinerary){
        List<DailySchedule> allDays = scheduleRepository.findByItinerary(itinerary);
         return allDays.size();
    }

    /**
     * creates a new day for a certain itinerary
     * @param itinerary itinerary the day belongs to
     * @return day saved to repository
     */
    public DailySchedule addNewDay(Itinerary itinerary) {
        DailySchedule timetable = new DailySchedule();
        timetable.setItinerary(itinerary);
        timetable.setName("Day " + (getNumberOfDays(itinerary) + 1));
        return scheduleRepository.save(timetable);
    }

    /**
     * saves changes to a day
     * @param dailySchedule selected day
     */
    public void saveDay(DailySchedule dailySchedule){
            scheduleRepository.save(dailySchedule);
    }

    /**
     * deletes selected day
     * @param dailySchedule selected day
     */
    public void removeDay(DailySchedule dailySchedule){
        scheduleRepository.delete(dailySchedule);
    }

    /**
     * deletes all days for a certain itinerary
     * @param itinerary itinerary the day belongs to
     */
    public void deleteAll(Itinerary itinerary){
        List<DailySchedule> toBeDeleted = getAllDays(itinerary);
        scheduleRepository.deleteInBatch(toBeDeleted);
    }

}
