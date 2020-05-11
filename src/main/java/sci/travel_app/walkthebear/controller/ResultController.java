package sci.travel_app.walkthebear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.service.PlacesServiceImp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ResultController {
    @Autowired
    private PlacesServiceImp placesService;


    //remove after testing or move to admin panel
    @GetMapping("/results/{page}")
    public String allResults(@PathVariable("page") int page, Model model){
        PageRequest pageable = PageRequest.of(page - 1, 15);
        Page<Place> resultPage = placesService.getPaginatedPlaceList(pageable);
        int totalPages = resultPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("activeResultList", true);
        model.addAttribute("resultList", resultPage.getContent());



        return "searchresults";
    }

    @GetMapping("/categories/tourist-attractions")
    public String categoryAttractions(Model model){
        //change this list
        List<Place> resultList = placesService.getAllPlaces();
        model.addAttribute("resultList", resultList);
        return "searchresults";
    }

    @GetMapping("/categories/lodging")
    public String categoryLodging(Model model){
        //change this list
        List<Place> resultList = placesService.getAllPlaces();
        model.addAttribute("resultList", resultList);
        return "searchresults";
    }
    @GetMapping("/categories/food-and-drink")
    public String categoryFood(Model model){
        //change this list
        List<Place> resultList = placesService.getAllPlaces();
        model.addAttribute("resultList", resultList);
        return "searchresults";
    }



}
