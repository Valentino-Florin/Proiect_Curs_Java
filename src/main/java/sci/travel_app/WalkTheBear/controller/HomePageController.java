package sci.travel_app.WalkTheBear.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.service.PlacesServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {
    @Autowired
    private PlacesServiceImp placeService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index( Model model) {
        List<Place> widget1 = new ArrayList<>();
        model.addAttribute("widget1", widget1);

        List<Place> widget2 = new ArrayList<>();
        model.addAttribute("widget2", widget2);

        List<Place> widget3 = new ArrayList<>();
        widget3.add(placeService.getPlaceById(1));
        model.addAttribute("widget3", widget3);
        return "index";
    }

}
