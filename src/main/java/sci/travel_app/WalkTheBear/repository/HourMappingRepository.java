package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.HourMapping;

import java.util.List;

@Repository
public interface HourMappingRepository extends JpaRepository<HourMapping, Long> {
    HourMapping findById(long id);
    List<HourMapping> findByDailySchedule(DailySchedule dailySchedule);
}
