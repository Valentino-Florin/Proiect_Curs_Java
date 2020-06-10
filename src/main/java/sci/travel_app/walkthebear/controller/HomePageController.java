package sci.travel_app.walkthebear.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.SpringCglibInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.service.PlacesServiceImp;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {
    @Autowired
    private PlacesServiceImp placeService;

    @GetMapping(value = "/")
    public String slashRedirect( Model model){
        return "redirect:/home";
    }
    @GetMapping(value = "/index")
    public String indexRedirect( Model model){
        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String home( Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        List<Place> widget1 = new ArrayList<>();
        model.addAttribute("widget1", widget1);

        List<Place> widget2 = new ArrayList<>();
        List<Place> newestPlaces = placeService.latestPlaces();
        widget2.add(newestPlaces.get(0));
        widget2.add(newestPlaces.get(1));
        widget2.add(newestPlaces.get(2));
        widget2.add(newestPlaces.get(3));
        widget2.add(newestPlaces.get(4));
        model.addAttribute("widget2", widget2);

        List<Place> widget3 = new ArrayList<>();
        widget3.add(placeService.getPlaceById(1));
        widget3.add(placeService.getPlaceById(2));
        widget3.add(placeService.getPlaceById(3));
        widget3.add(placeService.getPlaceById(4));
        widget3.add(placeService.getPlaceById(5));
        model.addAttribute("widget3", widget3);
        return "index";
    }

}
