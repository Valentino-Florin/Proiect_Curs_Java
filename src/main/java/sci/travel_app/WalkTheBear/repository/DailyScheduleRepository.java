package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;

import java.util.List;

@Repository
public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
    List<DailySchedule> findByUser(AppUser createdBy);
    DailySchedule findById(long id);

}
