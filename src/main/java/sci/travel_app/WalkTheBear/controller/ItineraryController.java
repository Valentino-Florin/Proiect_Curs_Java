package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.service.ItineraryService;
import sci.travel_app.WalkTheBear.service.ScheduleService;

import java.util.List;

@Controller
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;
    @Autowired
    private ScheduleService scheduleService;

//    @PostMapping("addItinerary")
//public String addNewItinerary (Itinerary itinerary, Model model) {
//        ItineraryService.createItinerary();
//        return "add-itinerary";
//    }

    //switch to find by user after user login is implemented
    @GetMapping("/tripmanager")
    public String getAllTrips(Model model){
        List<Itinerary> allTrips= itineraryService.findAll();
        model.addAttribute("allTrips", allTrips );
        return "tripmanager";
    }

    @GetMapping("/planner")
    public String createTrip(Model model) {
        model.addAttribute("itineraryForm", new Itinerary());
        return "planner";
    }

    @PostMapping("/planner/save")
    public String saveTrip(){

        return null;
    }


    @PutMapping("/planner/{id}")
    public Itinerary showTrip(@PathVariable(value = "id") Long id, Model model) {

        return itineraryService.findById(id);
//                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }






}