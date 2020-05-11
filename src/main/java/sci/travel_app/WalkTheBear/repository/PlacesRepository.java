package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.misc.Category;

import java.util.Date;
import java.util.List;

@Repository
public interface PlacesRepository extends JpaRepository<Place, Long> {
    List<Place> findByName(String name);
    List<Place> findByCategory(Category category);
    Place findById(long id);

    Page<Place> findAll(Pageable pageable);


//    List<Place> findLatest(Date date);
}