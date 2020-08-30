package sci.travel_app.walkthebear.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Rating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RatingServiceIntegrationTest {

    @Autowired
    private RatingServiceImpl ratingService;

    @Autowired
    private PlacesServiceImp placeService;

    @Test
    public void addARating() {

        // create a rating
        Rating arating = new Rating();
        arating.setPlace(placeService.getPlaceById(1));
        arating.setStarRating(4);
        arating.setComment("frumos");


        // test adding the rating
        AppUser user = new AppUser();
        Rating newRating = ratingService.create(arating, placeService.getPlaceById(1), user);

        // verify if created

        assertNotNull(newRating);
        assertNotNull(newRating.getPlace());
        assertEquals("frumos", newRating.getComment());

    }

}
