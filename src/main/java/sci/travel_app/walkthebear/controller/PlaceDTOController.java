package sci.travel_app.walkthebear.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sci.travel_app.walkthebear.data_utils.dto.PlaceDTO;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.service.PlacesServiceImp;
import sci.travel_app.walkthebear.service.RatingServiceImpl;

import java.util.List;

@Controller
public class PlaceDTOController {

    @Autowired
    private PlacesServiceImp placeService;
    @Autowired
    private RatingServiceImpl ratingService;
    @Autowired
    private ModelMapper modelMapper;

    //working variant; path: http://localhost:8080/places/1

//    @GetMapping(value="/places/{id}")
//    public String getPlace(@PathVariable("id") long id, Model model) {
//        Place place = placeService.getPlaceById(id);
//        model.addAttribute("place", place);
//        return "placedetail";
//    }

    @GetMapping(value="/places/{id}")
    public String getPlace(@PathVariable("id") long id, Model model) {
        PlaceDTO place = convertToDto(placeService.getPlaceById(id));
        List<Rating> ratingList = ratingService.getAllRatingsOfPlaceById(id);
        model.addAttribute("place", place);
        model.addAttribute("ratingList", ratingList);
        return "placedetailDTO";
    }


    @GetMapping(value="/places/{id}/addtotrip")
    public String addToTrip(@PathVariable("id") long id, Model model) {


        return "redirect:/places/" + id;
    }


    @GetMapping(value="/places/{id}/addtofavorites")
    public String addToFavorites(@PathVariable("id") long id, Model model) {


        return "redirect:/places/" + id;
    }

    private PlaceDTO convertToDto(Place place) {
        return modelMapper.map(place, PlaceDTO.class);
    }

}