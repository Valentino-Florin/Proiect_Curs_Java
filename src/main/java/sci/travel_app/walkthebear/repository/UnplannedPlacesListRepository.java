package sci.travel_app.walkthebear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.UnplannedPlacesList;

@Repository
public interface UnplannedPlacesListRepository extends JpaRepository<UnplannedPlacesList, Long> {

    UnplannedPlacesList findById(long id);
    UnplannedPlacesList findByUser(AppUser user);
}
