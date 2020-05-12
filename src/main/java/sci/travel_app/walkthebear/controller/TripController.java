package sci.travel_app.walkthebear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sci.travel_app.walkthebear.data_utils.dto.HourMappingDTO;
import sci.travel_app.walkthebear.model.entities.*;
import sci.travel_app.walkthebear.service.HourMappingService;
import sci.travel_app.walkthebear.service.ItineraryService;
import sci.travel_app.walkthebear.service.DailyScheduleService;
import sci.travel_app.walkthebear.service.PlacesServiceImp;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TripController {

    @Autowired
    private ItineraryService itineraryService;
    @Autowired
    private DailyScheduleService dailyScheduleService;
    @Autowired
    private HourMappingService hourMappingService;
    @Autowired
    private PlacesServiceImp placesService;

//Methods for trip manager page

    //switch to find by user after user login is implemented

    //show all itineraries
    @GetMapping("/tripmanager")
    public String getAllTrips(Model model) {
//        List<Itinerary> allTrips = itineraryService.findAll();
        model.addAttribute("allTrips", itineraryService.findAll());
        return "tripmanager";
    }

    //change mapping type

    //redirect to edit itinerary
//    @GetMapping("/tripmanager/edit/{id}")
//    public String editTrip(@PathVariable("id") long id, Model model) {
//
//
//        return null;
//    }


    //delete itinerary
    @GetMapping("/tripmanager/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") Long id, Model model) {
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        for (DailySchedule day : allDaysForItinerary) {
            hourMappingService.deleteAll(day);
        }
        dailyScheduleService.deleteAll(itineraryService.findById(id));
        itineraryService.deleteItinerary(itineraryService.findById(id));
        model.addAttribute("allTrips", itineraryService.findAll());
        return "tripmanager";
    }

//Methods for planner page

    //create new itinerary
    @GetMapping("/planner/new")
    public String createTrip(Model model) {
        model.addAttribute("itinerary", new Itinerary());
        return "planner";
    }

    //save itinerary
    @PostMapping("/planner/save")
    public String saveTrip(@ModelAttribute("trip") Itinerary trip,  BindingResult result) {
        Itinerary savedItinerary = itineraryService.saveItinerary(trip);
        return "redirect:/planner/" + savedItinerary.getId();
    }

    //view + edit itinerary
    @GetMapping("/planner/{id}")
    public String editTrip(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("itinerary" , itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", dailyScheduleService.getAllDays(itineraryService.findById(id)));
        return "planner";
    }

    @PostMapping("/planner/{id}/update")
    public String updateTrip (@PathVariable(value = "id") Long id, Model model) {
        itineraryService.update(itineraryService.findById(id));

        return "planner";
    }

    //view + print + download pdf
    @GetMapping("/planner/view/{id}")
    public String showTrip(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("itinerary" , itineraryService.findById(id));
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", allDaysForItinerary);
        for (DailySchedule day : allDaysForItinerary) {
            List<HourMapping> allHours = hourMappingService.getFullDay(day);
            model.addAttribute("allHours", allHours);
        }

        return "plannerview";
    }



//Methods for day page

    //create new day
    @GetMapping("/planner/{id}/addnewday")
    public String addNewDay(@PathVariable(value = "id") Long id, Model model) {
        DailySchedule newDay = dailyScheduleService.addNewDay(itineraryService.findById(id));
        hourMappingService.createDefaultDay(newDay);
        model.addAttribute("itinerary" , itineraryService.findById(id));
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", allDaysForItinerary);
        return "redirect:/planner/" + id;
    }



    //edit day + all hour mappings for that day
    @GetMapping("/planner/{id}/day/{dayID}")
    public String displayDay(@PathVariable(value = "id") Long id, @PathVariable(value = "dayID") Long dayId, Model model) {
        model.addAttribute("day", dailyScheduleService.getDay(dayId));

        List<HourMapping> hourObj = hourMappingService.getFullDay(dailyScheduleService.getDay(dayId));
        model.addAttribute("allHours", new HourMappingDTO(hourObj));

        //replace with an actual implementation; from here
        List<Place> unplannedPlaces = placesService.getAllPlaces();
        //to here
        model.addAttribute("unplannedPlaces", unplannedPlaces);

        return "editday";
    }



    //save edited day + all hour mappings for that day
    @PostMapping("/planner/{id}/day/{dayID}/save")
    public String saveDay(@PathVariable(value = "id") Long id, @PathVariable(value = "dayID") Long dayId, HourMappingDTO hoursDto, BindingResult result, Model model) {
//        if (result.hasErrors()) {
        dailyScheduleService.saveDay(dailyScheduleService.getDay(dayId));
        hourMappingService.saveAll(dailyScheduleService.getDay(dayId));
        return "editday";
    }










  //delete day + all hour mappings for that day
  @GetMapping("/planner/{id}/day/{dayID}/delete")
    public String removeDay(@PathVariable(value = "id") Long id, @PathVariable(value = "dayID") Long dayId, Model model) {
        hourMappingService.deleteAll(dailyScheduleService.getDay(dayId));
      dailyScheduleService.removeDay(dailyScheduleService.getDay(dayId));
      return "redirect:/planner/" + id;
    }
}
