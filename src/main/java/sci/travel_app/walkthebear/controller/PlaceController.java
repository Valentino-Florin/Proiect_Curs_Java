package sci.travel_app.walkthebear.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.service.PlacesServiceImp;


import javax.validation.Valid;

;import java.security.Principal;


@Controller
public class PlaceController {

    @Autowired
    private PlacesServiceImp placesService;
    @Autowired
    private AppUserRepository appUserRepository;

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PlaceController.class);
//    @Autowired
//    private RatingServiceImpl ratingService;


//    //path example: http://localhost:8080/places/1
//    @GetMapping(value="/places/{id}")
//    public String getPlace(@PathVariable("id") long id, Model model) {
//        Place place = placesService.getPlaceById(id);
//        List<Rating> ratingList = ratingService.getAllRatingsOfPlaceById(id);
//        model.addAttribute("place", place);
//        model.addAttribute("ratingList", ratingList);
//        return "placedetail";
//    }

    /**
     * Method used to populate "/placemanager" with the places specific to a logged user
     * @param model
     * @param principal
     * @return "placemanager"
     */

    @GetMapping("/placemanager")
    public String showPlaceManager(Model model, Principal principal) {
        model.addAttribute("userPlaces", placesService.findPlaceByUser(appUserRepository.findByUserName(principal.getName())));
        return "placemanager";
    }

    @GetMapping("/addplace")
    public String showNewPlaceForm(Model model) {
        model.addAttribute("place", new Place());
        return "addplace";
    }

    @PostMapping("/addplace")
    public String addNewPlace(@Valid Place place, BindingResult result, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        if (result.hasErrors()) {
            return "addplace";
        }

        placesService.addUserPlace(place, appUserRepository.findByUserName(principal.getName()));
        model.addAttribute("place", placesService.getAllPlaces());
        redirectAttributes.addFlashAttribute("message", "Place saved!");
        logger.log(Level.INFO, "Place added : "+ place );
        return "redirect:placemanager";
    }
    @GetMapping("/editplace/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Place place = placesService.getPlaceById(id);
        // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("place", place);
        return "editplace";
    }
    @PostMapping("/editplace/{id}")
    public String editPlace(@PathVariable("id") long id, @Valid Place place,
                              BindingResult result, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            place.setId(id);
            return "editplace";
        }

        placesService.updateUserPlace(place, appUserRepository.findByUserName(principal.getName()));
        model.addAttribute("userPlaces", placesService.findPlaceByUser(appUserRepository.findByUserName(principal.getName())));
        redirectAttributes.addFlashAttribute("message", "Place was updated");
        logger.log(Level.INFO, "Updated place: ID "+id);
        return "placemanager";
    }

    /**
     * Method used to delete a place, specific to a logged user, by using the ID
     * @param id - place ID
     * @param model
     * @param redirectAttributes
     * @param principal
     * @return "placemanager"
     * @throws IllegalArgumentException
     */
    @GetMapping("/deleteplace/{id}")
    public String erasePlace(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes, Principal principal) throws IllegalArgumentException {
        Place place = placesService.getPlaceById(id);
        //   .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        placesService.deletePlace(id);
        model.addAttribute("userPlaces", placesService.findPlaceByUser(appUserRepository.findByUserName(principal.getName())));
        redirectAttributes.addFlashAttribute("message", "Place was deleted");
        logger.log(Level.INFO, "Deleted place: ID "+id);
        return "placemanager";
    }
}