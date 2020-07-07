package sci.travel_app.walkthebear.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.service.*;

import java.security.Principal;
import java.util.List;


@Controller
public class PlaceDetailController {

    @Autowired
    private RatingServiceImpl ratingService;
    @Autowired
    private PlacesServiceImp placeService;
    @Autowired
    private FavoritesServiceImpl favoritesService;
    @Autowired
    private UnplannedPlacesListService unplannedPlacesListService;
    @Autowired
    private AppUserServiceImp appUserServiceImp;

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PlaceDetailController.class);


    @GetMapping(value = "/placedetail/{pid}")
    public String newRating(@PathVariable("pid") long id, Model model, Principal principal) {
        Place place = placeService.getPlaceById(id);
        model.addAttribute("place", place);
        Rating rating = new Rating();
        rating.setPlace(place);
        model.addAttribute("rating", rating);

        List<Rating> ratingList = ratingService.getAllRatingsOfPlaceById(id);
        model.addAttribute("ratingList", ratingList);

        double placeAverageRating = (ratingList.stream().mapToDouble(Rating::getStarRating).sum() / ratingList.stream().count());
        model.addAttribute("placeAverageRating", placeAverageRating);
        if (principal == null) {
            model.addAttribute("isAddedToFav", favoritesService.isAdded(placeService.getPlaceById(id), null));
            model.addAttribute("isAddedToList", unplannedPlacesListService.isAdded(placeService.getPlaceById(id), null));
        } else {
            model.addAttribute("isAddedToFav", favoritesService.isAdded(placeService.getPlaceById(id), appUserServiceImp.findByUserName(principal.getName())));
            model.addAttribute("isAddedToList", unplannedPlacesListService.isAdded(placeService.getPlaceById(id), appUserServiceImp.findByUserName(principal.getName())));
        }
        return "placedetail";
    }


    @PostMapping(value = "/placedetail/{pid}/sendReview")
    public String sendRating(@PathVariable("pid") long id, @ModelAttribute("rating") Rating rating, BindingResult result,
                             RedirectAttributes redirectAttributes) {

        ratingService.create(rating, placeService.getPlaceById(id));
        redirectAttributes.addFlashAttribute("message", "Success");
        return "redirect:/placedetail/" + id;
    }

    @GetMapping(value="/placedetail/{id}/addtofavorites")
    public String addToFavorites(@PathVariable("id") long id, Model model, Principal principal) {
        AppUser currentUser = appUserServiceImp.findByUserName(principal.getName());
     favoritesService.addToFavorites(placeService.getPlaceById(id), currentUser);
        return "redirect:/placedetail/" + id;
    }
    @GetMapping(value="/placedetail/{id}/removefromfavorites")
    public String removeFromFavorites(@PathVariable("id") long id, Model model, Principal principal) {
        favoritesService.removeFavorite(placeService.getPlaceById(id), appUserServiceImp.findByUserName(principal.getName()));
        return "redirect:/placedetail/" + id;
    }


    @GetMapping(value="/placedetail/{id}/addtolist")
    public String addToUnplannedPlaces(@PathVariable("id") long id, Model model, Principal principal) {
     if(!unplannedPlacesListService.hasList(appUserServiceImp.findByUserName(principal.getName()))){
         unplannedPlacesListService.createList(appUserServiceImp.findByUserName(principal.getName()));
     }
        unplannedPlacesListService.addToList(placeService.getPlaceById(id), unplannedPlacesListService.findByUser(appUserServiceImp.findByUserName(principal.getName())));
        return "redirect:/placedetail/" + id;
    }
    @GetMapping(value="/placedetail/{id}/removefromlist")
    public String removeFromUnplannedPlaces(@PathVariable("id") long id, Model model, Principal principal) {
       unplannedPlacesListService.removeFromList(placeService.getPlaceById(id), unplannedPlacesListService.findByUser(appUserServiceImp.findByUserName(principal.getName())));
        return "redirect:/placedetail/" + id;
    }

    @GetMapping("/profileratings")
    public String getAllRated(Model model) {
        List<Rating> allRated = ratingService.findAll();
        model.addAttribute("allRated", allRated);
        return "profileratings";
    }

}
