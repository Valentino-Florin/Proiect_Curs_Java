package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sci.travel_app.WalkTheBear.model.entities.Rating;
import sci.travel_app.WalkTheBear.service.RatingServiceImpl;

import java.util.logging.Logger;


@Controller
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

    private static final Logger log = Logger.getLogger(String.valueOf(RatingController.class));

    @GetMapping("/placedetail")
    public String newRating(Model model) {
        model.addAttribute("rating", new Rating());
        return "placedetail";
    }

    @PostMapping("/placedetail")
    public String save(Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        }

        ratingService.create(rating);
        model.addAttribute("rating", rating);
        return "placedetail";
    }

 /*   @RequestMapping(value = "sendRating", method = RequestMethod.POST)
    public String sendRating(@ModelAttribute("rating") Rating rating, @RequestParam("hdrating") int hdrating,
                             HttpSession session) {
        String username = session.getAttribute("username").toString();
        String placename = session.getAttribute("placename").toString();
        AppUserRepository appUserService;
        rating.setUser(appUserService.findByUserName(username));
        PlacesRepository placesRepository;
        rating.setPlace((Place) placesRepository.findByName(placename));
        rating.setCreated(new Date());
        rating.setStarRating(hdrating);
        ratingService.create(rating);

        return "redirect:/placedetail" + rating.getPlace().getId();

    }*/

  /*  @PostMapping(path = "/srating") // Map ONLY POST Requests
    public @ResponseBody
    String addNewRating(@RequestParam int starRating, @RequestParam String comment, @RequestParam AppUser user, @RequestParam Place place) {

        Rating nou = new Rating();
        nou.setStarRating(starRating);
        nou.setComment(comment);
        nou.setUser(user);
        nou.setPlace(place);


        ratingService.create(nou);
        return "Saved";
    }
*/
}


