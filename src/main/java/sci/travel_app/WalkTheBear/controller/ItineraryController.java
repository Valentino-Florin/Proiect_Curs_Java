package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.service.ItineraryService;

import java.util.List;

@Controller
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

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


    @PostMapping("/planner")
    public Itinerary createTrip(Itinerary trip) {
        return itineraryService.createItinerary();
    }


    @GetMapping("/planner/{id}")
    public Itinerary showTrip(@PathVariable(value = "id") Long id) {
        return itineraryService.findById(id);
//                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

//    @PutMapping("/planner/{id}")
//    public Itinerary updateTrip(@PathVariable(value = "id") Long id,
//                           @Valid @RequestBody Itinerary trip) {
//
//        Itinerary itinerary = itineraryService.findById(id);
//                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
//
//        itinerary.setName(trip.getName());
//        itinerary.setDescription(trip.getDescription());
//
//        Itinerary updatedTrip = itineraryService.saveItinerary();
//        return updatedTrip;
//    }

//    @DeleteMapping("/planner/{id}")
//    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
//        Itinerary itinerary = itineraryService.findById(id);
////                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
//
//        itineraryService.deleteItinerary();
//
//        return ResponseEntity.ok().build();
//    }

//    //alternate update method; update the requestmapping value!
//    @RequestMapping(value = {id}, method = RequestMethod.PUT)
//    public Itinerary update(@PathVariable Long id, @RequestBody Itinerary itinerary){
//        Itinerary existingItinerary = itineraryService.findById(id);
//        BeanUtils.copyProperties(itinerary, existingItinerary, "id");
//        return itineraryService.saveItinerary(existingItinerary);
//    }


}