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


public HourMapping getHour(long id){
   return hourMappingRepository.findById(id);
}

public List<HourMapping> getAllHours(){
    return hourMappingRepository.findAll();
}
    public void createMapping(DailySchedule dailySchedule, String time, Place place){
        HourMapping slot = new HourMapping();
        slot.setDailySchedule(dailySchedule);
        slot.setPlace(place);
        slot.setTime(time);
        hourMappingRepository.save(slot);
    }

    public void saveMapping(HourMapping hourMapping, DailySchedule dailySchedule){
        hourMapping.setDailySchedule(dailySchedule);
        hourMappingRepository.save(hourMapping);
    }

    public void updateMapping(HourMapping hourMapping, DailySchedule dailySchedule, long id){
       HourMapping saveMe = hourMappingRepository.findById(id);
        saveMe.setHourId(id);
        saveMe.setTime(hourMapping.getTime());
        saveMe.setPlace(hourMapping.getPlace());
        saveMe.setDailySchedule(dailySchedule);
        hourMappingRepository.save(saveMe);
    }

    public List<HourMapping> getFullDay(DailySchedule dailySchedule){
       return hourMappingRepository.findByDailySchedule(dailySchedule);
    }

    public void deleteOne(HourMapping hourMapping){
    hourMappingRepository.delete(hourMapping);
    }

    public void deleteAll(DailySchedule dailySchedule){
        List<HourMapping> toBeDeleted = getFullDay(dailySchedule);
        hourMappingRepository.deleteInBatch(toBeDeleted);
//        for (HourMapping axed : toBeDeleted){
//            hourMappingRepository.delete(axed);}
        }

    public void saveFullDay(DailySchedule dailySchedule){
        List<HourMapping> allHours = getFullDay(dailySchedule);
        hourMappingRepository.saveAll(allHours);
    }
   public void saveAll(List<HourMapping> list, DailySchedule dailySchedule){
       for (HourMapping hour : list) {
           long id = hour.getHourId();
           HourMapping saveMe = hourMappingRepository.findById(id);
           saveMe.setDailySchedule(dailySchedule);
           hourMappingRepository.save(saveMe);
       }
   }
}
