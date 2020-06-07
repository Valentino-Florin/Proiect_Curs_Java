package sci.travel_app.walkthebear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.service.FavoritesServiceImpl;
import sci.travel_app.walkthebear.service.PlacesServiceImp;
import sci.travel_app.walkthebear.service.RatingServiceImpl;
import sci.travel_app.walkthebear.service.UnplannedPlacesListService;

import java.util.List;
import java.util.logging.Logger;


@Controller
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;
    @Autowired
    private PlacesServiceImp placeService;
    @Autowired
    private FavoritesServiceImpl favoritesService;
    @Autowired
    private UnplannedPlacesListService unplannedPlacesListService;

    private static final Logger log = Logger.getLogger(String.valueOf(RatingController.class));

    @GetMapping(value="/placedetail/{id}")
    public String newRating(@PathVariable("id") long id, Model model) {
        Place place = placeService.getPlaceById(id);
        model.addAttribute("place", place);
        Rating rating = new Rating();
        rating.setPlace(place);
        model.addAttribute("rating", rating);
        List<Rating> ratingList = ratingService.getAllRatingsOfPlaceById(id);
        model.addAttribute("ratingList", ratingList);
        model.addAttribute("isAddedToFav", favoritesService.isAdded2(placeService.getPlaceById(id)));
        model.addAttribute("isAddedToList", unplannedPlacesListService.isAdded(placeService.getPlaceById(id), null));
        return "placedetail";
    }


    @PostMapping(value="/placedetail/{id}/sendReview")
    public String sendRating(@PathVariable("id") long id, @ModelAttribute("rating") Rating rating, BindingResult result,
                             RedirectAttributes redirectAttributes)
    {
        ratingService.create(rating, placeService.getPlaceById(id));
        redirectAttributes.addFlashAttribute("message", "Success");
        return "redirect:/placedetail/" + id;
    }

    @GetMapping(value="/placedetail/{id}/addtofavorites")
    public String addToFavorites(@PathVariable("id") long id, Model model) {
     favoritesService.addToFavorites(placeService.getPlaceById(id));
        return "redirect:/placedetail/" + id;
    }
    @GetMapping(value="/placedetail/{id}/removefromfavorites")
    public String removeFromFavorites(@PathVariable("id") long id, Model model) {
        favoritesService.removeFavorite(placeService.getPlaceById(id), null);
        return "redirect:/placedetail/" + id;
    }


    @GetMapping(value="/placedetail/{id}/addtolist")
    public String addToUnplannedPlaces(@PathVariable("id") long id, Model model) {
     if(unplannedPlacesListService.getAll().isEmpty()){
         unplannedPlacesListService.createList(null);
     }
        unplannedPlacesListService.addToList(placeService.getPlaceById(id), unplannedPlacesListService.findByUser(null));
        return "redirect:/placedetail/" + id;
    }
    @GetMapping(value="/placedetail/{id}/removefromlist")
    public String removeFromUnplannedPlaces(@PathVariable("id") long id, Model model) {
       unplannedPlacesListService.removeFromList(placeService.getPlaceById(id), unplannedPlacesListService.findByUser(null));
        return "redirect:/placedetail/" + id;
    }
    }

