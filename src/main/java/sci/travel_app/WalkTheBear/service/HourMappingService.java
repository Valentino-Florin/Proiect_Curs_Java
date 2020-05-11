package sci.travel_app.WalkTheBear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.HourMapping;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.repository.HourMappingRepository;

import java.util.List;

@Service
public class HourMappingService {
    @Autowired
   private HourMappingRepository hourMappingRepository;

    public void createMapping(DailySchedule dailySchedule, String time, Place place){
        HourMapping slot = new HourMapping();
        slot.setDailySchedule(dailySchedule);
        slot.setPlace(place);
        slot.setTime(time);
        hourMappingRepository.save(slot);
    }
    public void updateMapping(String time, Place place, HourMapping hourMapping){
      if(hourMapping.getTime().equals(time)){
          hourMapping.setPlace(place);
          hourMappingRepository.save(hourMapping);
      }
      //else...some kind of invalid argument exception?
    }

    public List<HourMapping> getFullDay(DailySchedule dailySchedule){
       return hourMappingRepository.findByDailySchedule(dailySchedule);
    }
    public void createDefaultDay(DailySchedule dailySchedule){
        createMapping(dailySchedule,"00:00" , null);
        createMapping(dailySchedule,"01:00" , null);
        createMapping(dailySchedule,"02:00" , null);
        createMapping(dailySchedule,"03:00" , null);
        createMapping(dailySchedule,"04:00" , null);
        createMapping(dailySchedule,"05:00" , null);
        createMapping(dailySchedule,"06:00" , null);
        createMapping(dailySchedule,"07:00" , null);
        createMapping(dailySchedule,"08:00" , null);
        createMapping(dailySchedule,"09:00" , null);
        createMapping(dailySchedule,"10:00" , null);
        createMapping(dailySchedule,"11:00" , null);
        createMapping(dailySchedule,"12:00" , null);
        createMapping(dailySchedule,"13:00" , null);
        createMapping(dailySchedule,"14:00" , null);
        createMapping(dailySchedule,"15:00" , null);
        createMapping(dailySchedule,"16:00" , null);
        createMapping(dailySchedule,"17:00" , null);
        createMapping(dailySchedule,"18:00" , null);
        createMapping(dailySchedule,"19:00" , null);
        createMapping(dailySchedule,"20:00" , null);
        createMapping(dailySchedule,"21:00" , null);
        createMapping(dailySchedule,"22:00" , null);
        createMapping(dailySchedule,"23:00" , null);
    }

    public void deleteAll(DailySchedule dailySchedule){
        List<HourMapping> toBeDeleted = getFullDay(dailySchedule);
        for (HourMapping axed : toBeDeleted){
            hourMappingRepository.delete(axed);}
        }

    public void saveAll(DailySchedule dailySchedule){
        List<HourMapping> allHours = getFullDay(dailySchedule);
        hourMappingRepository.saveAll(allHours);
    }
}
