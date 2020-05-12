package sci.travel_app.walkthebear.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.model.misc.SubCategory;

import java.util.Date;
import java.util.List;

@Repository
public interface PlacesRepository extends JpaRepository<Place, Long> {
    Place findById(long id);

    List<Place> findByName(String name);

    List<Place> findByCategory(Category category);

    Page<Place> findByCategory(Category category, Pageable pageable);

    Page<Place> findBySubcategory(SubCategory subCategory, Pageable pageable);
    Page<Place> findByCounty(String county, Pageable pageable);
    Page<Place> findByCity(String city, Pageable pageable);
    Page<Place> findByCreated(Date created, Pageable pageable);


    Page<Place> findAll(Pageable pageable);


}