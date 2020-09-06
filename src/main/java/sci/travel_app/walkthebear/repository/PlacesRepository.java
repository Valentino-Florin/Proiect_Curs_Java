package sci.travel_app.walkthebear.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.model.misc.SubCategory;

import java.util.Date;
import java.util.List;

@Repository
public interface PlacesRepository extends JpaRepository<Place, Long> {
    Place findById(long id);

    List<Place> findByName(String name);

    List<Place> findPlaceByUser(AppUser createdBy);

    List<Place> findByCategory(Category category);

     @Query(value = "SELECT * FROM travel_app.place p WHERE  p.NAME LIKE '%' :keyword '%'", nativeQuery=true)
     List<Place> findByKeyword(@Param("keyword") String keyword);
//    p.PLACE_ID LIKE '%'  :keyword  '%'"
//            + " OR
//+ " OR c.COUNTY LIKE '%'  :keyword  '%'"
//        + " OR p.CITY LIKE '%'  :keyword  '%'"
//        + " OR c.ADDRESS LIKE '%'  :keyword  '%'"
//        + " OR p.COORDINATES LIKE '%'  :keyword  '%'"
//        + " OR c.PHONE_NUMBER LIKE '%'  :keyword  '%'"
//        + " OR c.EMAIL LIKE '%'  :keyword  '%'"
//        + " OR p.CATEGORY LIKE '%'  :keyword '%'"
//        + " OR c.SUBCATEGORY LIKE '%'  :keyword  '%'"

    Page<Place> findByCategory(Category category, Pageable pageable);
    Page<Place> findBySubcategory(SubCategory subCategory, Pageable pageable);
    Page<Place> findByCounty(String county, Pageable pageable);
    Page<Place> findByCity(String city, Pageable pageable);
//    Page<Place> findByCreated(Date created, Pageable pageable);
    Page<Place> findByNameContains(String keyword,Pageable pageable);
    Page<Place> findBySubcategoryAndCounty(SubCategory subCategory, String county, Pageable pageable);
    Page<Place> findBySubcategoryAndCity(SubCategory subCategory, String city, Pageable pageable);
    Page<Place> findBySubcategoryAndCountyAndCity(SubCategory subCategory,String county, String city, Pageable pageable);
    List <Place> findAllByOrderByCreatedDesc();

    Page<Place> findAll(Pageable pageable);


}