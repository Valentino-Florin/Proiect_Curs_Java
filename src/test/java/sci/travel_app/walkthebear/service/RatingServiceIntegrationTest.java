package sci.travel_app.walkthebear.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.repository.AppUserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RatingServiceIntegrationTest {

    @Autowired
    private RatingServiceImpl ratingService;

    @Autowired
    private PlacesServiceImp placeService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void addARating() {

        // create a rating
        Rating arating = new Rating();
        arating.setUser(appUserRepository.findById(1));
        arating.setPlace(placeService.getPlaceById(1));
        arating.setStarRating(4);
        arating.setComment("frumos");


        // test adding the rating
        Rating newRating = ratingService.create(arating, placeService.getPlaceById(1), appUserRepository.findById(1));

        // verify if created

        assertNotNull(newRating);
        assertNotNull(newRating.getPlace());
        assertNotNull(newRating.getUser());
        assertEquals("frumos", newRating.getComment());

    }

}
