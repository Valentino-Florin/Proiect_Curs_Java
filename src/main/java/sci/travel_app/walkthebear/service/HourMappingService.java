package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.DailySchedule;
import sci.travel_app.walkthebear.model.entities.HourMapping;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.repository.HourMappingRepository;

import java.util.List;

@Service
public class HourMappingService {
    @Autowired
    private HourMappingRepository hourMappingRepository;

    /**
     * finds an HourMapping object by id
     * @param id id of HourMapping object
     * @return HourMapping object
     */
    public HourMapping getHour(long id){
        return hourMappingRepository.findById(id);
    }

    //not used
    public List<HourMapping> getAllHours(){
        return hourMappingRepository.findAll();
    }
    //not used
    public void createMapping(DailySchedule dailySchedule, String time, Place place){
        HourMapping slot = new HourMapping();
        slot.setDailySchedule(dailySchedule);
        slot.setPlace(place);
        slot.setTime(time);
        hourMappingRepository.save(slot);
    }

    /**
     * takes an HourMapping object, sets the value for DailySchedule and saves the HourMapping to repository
     * @param hourMapping HourMapping object to save
     * @param dailySchedule related DailySchedule object
     */
    public void saveMapping(HourMapping hourMapping, DailySchedule dailySchedule){
        hourMapping.setDailySchedule(dailySchedule);
        hourMappingRepository.save(hourMapping);
    }

    /**
     * creates a new object and passes all the values from the old object, then saves the new object in the repository
     * @param hourMapping HourMapping object to update
     * @param dailySchedule related DailySchedule object
     * @param id HourMapping object id
     */
    public void updateMapping(HourMapping hourMapping, DailySchedule dailySchedule, long id){
        HourMapping saveMe = hourMappingRepository.findById(id);
        saveMe.setHourId(id);
        saveMe.setTime(hourMapping.getTime());
        saveMe.setPlace(hourMapping.getPlace());
        saveMe.setDailySchedule(dailySchedule);
        hourMappingRepository.save(saveMe);
    }

    /**
     * finds all HourMapping objects associated with a given DailySchedule
     * @param dailySchedule related DailySchedule object
     * @return returns a list of HourMapping objects
     */
    public List<HourMapping> getFullDay(DailySchedule dailySchedule){
        return hourMappingRepository.findByDailySchedule(dailySchedule);
    }

    /**
     * deletes one HourMapping object
     * @param hourMapping HourMapping object to delete
     */
    public void deleteOne(HourMapping hourMapping){
        hourMappingRepository.delete(hourMapping);
    }

    /**
     * finds all HourMapping objects associated with a given DailySchedule and deletes them
     * @param dailySchedule related DailySchedule object
     */
    public void deleteAll(DailySchedule dailySchedule){
        List<HourMapping> toBeDeleted = getFullDay(dailySchedule);
        hourMappingRepository.deleteInBatch(toBeDeleted);
    }
//not used
    public void saveFullDay(DailySchedule dailySchedule){
        List<HourMapping> allHours = getFullDay(dailySchedule);
        hourMappingRepository.saveAll(allHours);
    }
    //not used
    public void saveAll(List<HourMapping> list, DailySchedule dailySchedule){
        for (HourMapping hour : list) {
            long id = hour.getHourId();
            HourMapping saveMe = hourMappingRepository.findById(id);
            saveMe.setDailySchedule(dailySchedule);
            hourMappingRepository.save(saveMe);
        }
    }
}
