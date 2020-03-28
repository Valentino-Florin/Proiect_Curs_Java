package sci.travel_app.WalkTheBear.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sci.travel_app.WalkTheBear.data_utils.dto.PlaceDTO;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.service.PlaceService;
import sci.travel_app.WalkTheBear.service.RatingService;

@Controller
public class PlaceDTOController {

    @Autowired
    private PlaceService placeService;
    @Autowired
    private RatingService ratingService;
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
        model.addAttribute("place", place);
        return "placedetailDTO";
    }


    private PlaceDTO convertToDto(Place place) {
        PlaceDTO placeDto = modelMapper.map(place, PlaceDTO.class);

        return placeDto;
    }

}