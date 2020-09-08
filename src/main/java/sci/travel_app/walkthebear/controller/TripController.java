package sci.travel_app.walkthebear.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.data_utils.FileService;
import sci.travel_app.walkthebear.model.entities.*;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;


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
    @Autowired
    private UnplannedPlacesListService unplannedPlacesListService;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserServiceImp appUserServiceImp;
    @Autowired
    private FileService fileService;

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(TripController.class);

    public Map <String, List<HourMapping>> itineraryMap(List <DailySchedule> allDaysForItinerary){
        Map<String, List<HourMapping>> timeTable = new HashMap<>();
        for (DailySchedule day : allDaysForItinerary) {
            timeTable.put(day.getName(),hourMappingService.getFullDay(day));
        }
        return timeTable;
    }



//mappings for trip manager page


    @GetMapping("/tripmanager")
    public String getAllTrips(Model model, Principal principal) {
        model.addAttribute("allTrips", itineraryService.findByUser(appUserServiceImp.findByUserName(principal.getName())));
        return "tripmanager";
    }


    //delete itinerary
    @GetMapping("/tripmanager/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes, Model model) {
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        for (DailySchedule day : allDaysForItinerary) {
            hourMappingService.deleteAll(day);
        }
        dailyScheduleService.deleteAll(itineraryService.findById(id));
        itineraryService.deleteItinerary(itineraryService.findById(id));
        model.addAttribute("allTrips", itineraryService.findAll());
        redirectAttributes.addFlashAttribute("message", "Trip was deleted");
        logger.log(Level.INFO, "Deleted itinerary: ID "+id);
        return "redirect:/tripmanager";
    }

//Methods for planner page

    //create new itinerary
    @GetMapping("/planner/new")
    public String createTrip(Model model) {
        model.addAttribute("itinerary", new Itinerary());
        return "planneradd";
    }

    //save itinerary
    @PostMapping("/planner/save")
    public String saveTrip(@ModelAttribute("trip") Itinerary trip,  BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {
        AppUser currentUser = appUserServiceImp.findByUserName(principal.getName());
        Itinerary savedItinerary = itineraryService.saveItinerary(trip, currentUser);
        logger.log(Level.INFO, "Created new itinerary: ID "+ savedItinerary.getItineraryId());
        redirectAttributes.addFlashAttribute("message", "Trip saved!");
        return "redirect:/planner/" + savedItinerary.getItineraryId();
    }


    //view + edit itinerary
    @GetMapping("/planner/{id}")
    public String editTrip(@PathVariable(value = "id") long id, Model model, Principal principal) {
        model.addAttribute("itinerary" , itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", dailyScheduleService.getAllDays(itineraryService.findById(id)));
        return "planneredit";
    }

    @PostMapping("/planner/{id}/update")
    public String updateTrip (@PathVariable(value = "id") long id, @Valid Itinerary itinerary, BindingResult result, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Could not update");
            return "planneredit";
        }
        model.addAttribute("itinerary" , itineraryService.findById(id));
        itineraryService.update(itinerary, id, appUserServiceImp.findByUserName(principal.getName()));
        redirectAttributes.addFlashAttribute("message", "Trip updated!");
        return "redirect:/planner/" + id;
    }

    //view + print + download json
    @GetMapping("/planner/view/{id}")
    public String showTrip(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("itinerary" , itineraryService.findById(id));
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", allDaysForItinerary);
        model.addAttribute("timetable", itineraryMap(allDaysForItinerary));
        return "plannerview";
    }


    @GetMapping("/planner/view/{id}/print")
    public String showAndPrintTrip(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("itinerary" , itineraryService.findById(id));
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", allDaysForItinerary);
        model.addAttribute("timetable", itineraryMap(allDaysForItinerary));
        return "plannerview_print";
    }


    @GetMapping("/planner/view/{id}/json")
//    public String downloadJSON(@PathVariable(value = "id") long id, Model model) {
//        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
//        fileService.mapToJson(id, itineraryMap(allDaysForItinerary));
//
//        return "redirect:/planner/view/" + id;
//    @ResponseBody
//    public void downloadJSON(HttpServletRequest request,
//                             HttpServletResponse response, @PathVariable(value = "id") long id){
//        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
//        fileService.mapToJson(id, itineraryMap(allDaysForItinerary));
//
//
//        String dataDirectory = request.getServletContext().getRealPath("src/main/resources/static/files/json/");
//        String fileName = "itinerary" + id + ".json";
//        Path file = Paths.get(dataDirectory, fileName);
//        if (Files.exists(file))
//        {
//            response.setContentType("application/json");
//            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
//            //                Files.copy(file, response.getOutputStream());
////                response.getOutputStream().flush();
//
//        }
//    }
    public ResponseEntity<Resource> downloadJSON (@PathVariable(value = "id") long id) throws FileNotFoundException {
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        fileService.mapToJson(id, itineraryMap(allDaysForItinerary));

       String file = "itinerary" + id + ".json";
        String filepath =  "src/main/resources/static/files/json/" + "itinerary" + id + ".json";
        InputStreamResource resource = new InputStreamResource(new FileInputStream(filepath));
        HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file);
//        byte[] data = Files.readAllBytes(path);
//        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }



//Methods for day page


    @GetMapping("/planner/{id}/addnewday")
    public String addNewDay(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes, Model model) {
        DailySchedule newDay = dailyScheduleService.addNewDay(itineraryService.findById(id));
        model.addAttribute("itinerary" , itineraryService.findById(id));
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", allDaysForItinerary);
        redirectAttributes.addFlashAttribute("message", "New day successfully added");
        logger.log(Level.INFO, "Created new day: ID " + newDay.getDayId());
        return "redirect:/planner/" + id;
    }

    @GetMapping("/planner/{id}/day/{dayID}")
    public String displayDay(@PathVariable(value = "id") long id, @PathVariable(value = "dayID") long dayId, Model model, Principal principal) {
        model.addAttribute("itinerary", itineraryService.findById(id));
        model.addAttribute("day", dailyScheduleService.getDay(dayId));
        List<HourMapping> allHours= hourMappingService.getFullDay(dailyScheduleService.getDay(dayId));
        model.addAttribute("allHours", allHours);
        HourMapping objective = new HourMapping();
        objective.setDailySchedule(dailyScheduleService.getDay(dayId));
        model.addAttribute("objective", objective);
        if(!unplannedPlacesListService.hasList(appUserServiceImp.findByUserName(principal.getName()))) {
            unplannedPlacesListService.createList(appUserServiceImp.findByUserName(principal.getName()));
        }
        List<Place> unplannedPlaces = unplannedPlacesListService.findByUser(appUserServiceImp.findByUserName(principal.getName())).getUnplannedPlacesTemp();
        model.addAttribute("unplannedPlaces", unplannedPlaces);


        return "day_add";
    }



    @PostMapping("/planner/{id}/day/{dayID}/save")
    public String saveDay(@PathVariable(value = "id") long itineraryID, @PathVariable(value = "dayID") long dayId, @ModelAttribute("objective") HourMapping hourMapping, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Could not save");
            return "day_add";
        }
        model.addAttribute("itinerary", itineraryService.findById(itineraryID));
        model.addAttribute("day", dailyScheduleService.getDay(dayId));
        dailyScheduleService.saveDay(dailyScheduleService.getDay(dayId));
        hourMappingService.saveMapping(hourMapping, dailyScheduleService.getDay(dayId));
        redirectAttributes.addFlashAttribute("message", "Day was successfully updated");
        logger.log(Level.INFO, "Saved hourmapping: ID "+dayId);
        return "redirect:/planner/"+itineraryID+"/day/"+ dayId;
    }

    //edit hour mapping wip
    @GetMapping("/planner/{id}/day/{dayID}/edit/{hourId}")
    public String editObjective(@PathVariable(value = "id") long id, @PathVariable(value = "dayID") long dayId, @PathVariable(value = "hourId") long hourId, Model model, Principal principal) {
        model.addAttribute("itinerary", itineraryService.findById(id));
        model.addAttribute("day", dailyScheduleService.getDay(dayId));
        List<HourMapping> allHours= hourMappingService.getFullDay(dailyScheduleService.getDay(dayId));
        model.addAttribute("allHours", allHours);
        HourMapping objective = hourMappingService.getHour(hourId);
        model.addAttribute("objective", objective);
        model.addAttribute("hour",hourMappingService.getHour(hourId));
        if(!unplannedPlacesListService.hasList(appUserServiceImp.findByUserName(principal.getName()))) {
            unplannedPlacesListService.createList(appUserServiceImp.findByUserName(principal.getName()));
        }
        List<Place> unplannedPlaces = unplannedPlacesListService.findByUser(appUserServiceImp.findByUserName(principal.getName())).getUnplannedPlacesTemp();
        model.addAttribute("unplannedPlaces", unplannedPlaces);
        return "day_update";
    }
    //update hour mapping wip
    @PostMapping("/planner/{id}/day/{dayID}/update/{hoursId}")
    public String updateHour(@PathVariable(value = "id") long itineraryID, @PathVariable(value = "dayID") long dayId, @PathVariable(value = "hoursId") long hourId, @Valid HourMapping hourMapping, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Could not update");
            return "day_update";
        }
        model.addAttribute("itinerary", itineraryService.findById(itineraryID));
        model.addAttribute("day", dailyScheduleService.getDay(dayId));
        model.addAttribute("hour",hourMappingService.getHour(hourId));
        hourMappingService.updateMapping(hourMapping, dailyScheduleService.getDay(dayId), hourId);
        redirectAttributes.addFlashAttribute("message", "Day was successfully updated");
        logger.log(Level.INFO, "Updated hourmapping: ID " + dayId);
        return "redirect:/planner/"+itineraryID+"/day/"+ dayId;
    }

    //delete hour mapping wip
    @GetMapping("/planner/{id}/day/{dayID}/delete/{hourId}")
    public String deleteObjective(@PathVariable(value = "id") long itineraryID, @PathVariable(value = "dayID") long dayId, @PathVariable(value = "hourId") long hourId, RedirectAttributes redirectAttributes, Model model) {
        hourMappingService.deleteOne(hourMappingService.getHour(hourId));
        List<HourMapping> allHours= hourMappingService.getFullDay(dailyScheduleService.getDay(dayId));
        model.addAttribute("allHours", allHours);
        redirectAttributes.addFlashAttribute("message", "Objective removed");
        logger.log(Level.INFO, "Deleted : ID " + hourId);

        return "redirect:/planner/"+itineraryID+"/day/"+ dayId;
    }


    //delete day + all hour mappings for that day
    @GetMapping("/planner/{id}/day/{dayID}/delete")
    public String removeDay(@PathVariable(value = "id") long id, @PathVariable(value = "dayID") long dayId, RedirectAttributes redirectAttributes, Model model) {

        hourMappingService.deleteAll(dailyScheduleService.getDay(dayId));
        dailyScheduleService.removeDay(dailyScheduleService.getDay(dayId));
        redirectAttributes.addFlashAttribute("message", "Day was successfully removed");
        logger.log(Level.INFO, "Deleted day: ID "+id);
        return "redirect:/planner/" + id;
    }
}
