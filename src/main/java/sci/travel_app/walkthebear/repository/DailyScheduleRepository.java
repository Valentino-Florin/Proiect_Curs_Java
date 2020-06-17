package sci.travel_app.walkthebear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.DailySchedule;
import sci.travel_app.walkthebear.model.entities.Itinerary;

import java.util.List;

@Repository
public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
    DailySchedule findById(long id);
    List<DailySchedule> findByItinerary(Itinerary itinerary);
    DailySchedule findByNameAndItinerary(String name, Itinerary itinerary);
}
