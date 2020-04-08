package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sci.travel_app.WalkTheBear.data_utils.dto.PlaceDTO;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.entities.Rating;
import sci.travel_app.WalkTheBear.model.misc.Category;
import sci.travel_app.WalkTheBear.service.PlacesService;
import sci.travel_app.WalkTheBear.service.PlacesServiceImp;
import sci.travel_app.WalkTheBear.service.RatingServiceImpl;

import javax.validation.Valid;;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PlaceController {

    @Autowired
    private PlacesServiceImp placesService;
//    @Autowired
//    private RatingServiceImpl ratingService;

    /* @PostMapping(value = "/addplace")
      public String addNewPlace( @ModelAttribute Place place )
      {
      placesService.addPlace(place);
          return "redirect:placemanager";
      } */
    @GetMapping("/addplace")
    public String showNewPlaceForm(Model model) {
        model.addAttribute("place", new Place());
        return "addplace";
    }

    @PostMapping("/addplace")
    public String addNewPlace(@Valid Place place, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addplace";
        }

        placesService.addPlace(place);
        model.addAttribute("place", placesService.getAllPlaces());
        return "redirect:placemanager";
    }

    @GetMapping("/view")
    public String showPlace(Model model, String placeName) {
        model.addAttribute("place", placesService.getPlaceByName(placeName));
        return "searchresults";
    }

//    //path example: http://localhost:8080/places/1
//    @GetMapping(value="/places/{id}")
//    public String getPlace(@PathVariable("id") long id, Model model) {
//        Place place = placesService.getPlaceById(id);
//        List<Rating> ratingList = ratingService.getAllRatingsOfPlaceById(id);
//        model.addAttribute("place", place);
//        model.addAttribute("ratingList", ratingList);
//        return "placedetail";
//    }

    @GetMapping("/category")
    public String showPlacesByCategory(Model model, Category category) {
        model.addAttribute("category", placesService.getPlaceByCategory(category));
        return "categoryresults";
    }

    @PostMapping("/update/{id}")
    public String changePlace(@PathVariable("id") long id, @Valid Place place,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            place.setId(id);
            return "update-place";
        }

        placesService.updatePlace(place);
        model.addAttribute("place", placesService.getAllPlaces());
        return "placemanager";
    }

    @GetMapping("/delete/{id}")
    public String erasePlace(@PathVariable("id") long id, Model model) throws IllegalArgumentException {
        Place place = placesService.getPlaceById(id);
        //   .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        placesService.deletePlace(id);
        model.addAttribute("place", placesService.getAllPlaces());
        return "placemanager";
    }

}
