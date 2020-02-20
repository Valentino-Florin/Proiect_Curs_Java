package sci.travel_app.WalkTheBear.repository;

        import org.springframework.stereotype.Repository;
        import sci.travel_app.WalkTheBear.model.Entities.AppUser;
        import org.springframework.data.repository.CrudRepository;
        import sci.travel_app.WalkTheBear.model.Entities.Itinerary;

        import java.util.List;

@Repository
public interface ItineraryRepository extends CrudRepository<Itinerary, Long> {
    List<Itinerary> findByUser(AppUser createdBy);

    Itinerary findById(long id);

}