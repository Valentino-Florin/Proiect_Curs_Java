package sci.travel_app.walkthebear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.service.PlacesServiceImp;

import java.util.List;

@Controller
//@RequestMapping(value="/results")
public class ResultController {
    @Autowired
    private PlacesServiceImp placesService;


//    //remove after testing or move to admin panel
//    @GetMapping("/results/{page}")
//    public String allResults(@PathVariable("page") int page, Model model){
//        PageRequest pageable = PageRequest.of(page - 1, 15);
//        Page<Place> resultPage = placesService.getPaginatedPlaceList(pageable, Category.ATTRACTIONS);
//        int totalPages = resultPage.getTotalPages();
//        if(totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//        model.addAttribute("activeResultList", true);
//        model.addAttribute("resultList", resultPage.getContent());
//
//
//        return "searchresults";
//    }



    // ------------------------search------------------------------------//

    @GetMapping("/results")
    public String searchResultsPage1(Model model, String keyword) {
        return searchResults(model, 1, keyword, "name", "asc");
    }
    @GetMapping("/results/{pageNum}")
    public String searchResults(Model model,
                                @PathVariable(name = "pageNum") int pageNum,
                                @Param("keyword") String keyword,
                                @Param("sortField") String sortField,
                                @Param("sortDir") String sortDir){

        Page<Place> page = placesService.getPaginatedPlaceListByKeyword(pageNum,sortField,sortDir,keyword);
        List<Place> resultList = page.getContent();
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("resultList", resultList);

        return "searchresults";
    }



    @GetMapping("/results/filtered/{pageNum}")
    public String searchResultsFiltered(Model model,
                                @PathVariable(name = "pageNum") int pageNum,
                                @Param("keyword") String keyword,
                                @Param("sortField") String sortField,
                                @Param("sortDir") String sortDir,
                                @Param("subcategory") String subcategory,
                                @Param("county") String county,
                                @Param("city") String city){

        Page<Place> page = placesService.getPaginatedPlaceListByKeyword(pageNum,sortField,sortDir,keyword);
        List<Place> resultList = page.getContent();
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("resultList", resultList);

        return "searchresults";
    }
    // ------------------------categories------------------------------------//

    @GetMapping("/categories/tourist-attractions")
    public String categoryAttractionsPage1(Model model){
        return categoryAttractions(model,1,"name", "asc");
    }
    @GetMapping("/categories/lodging")
    public String categoryLodgingPage1(Model model){
        return categoryLodging(model,1, "name", "asc");
    }
    @GetMapping("/categories/food-and-drink")
    public String categoryFoodAndDrinkPage1(Model model){
        return categoryFood(model,1, "name", "asc");
    }

    @GetMapping("/categories/tourist-attractions/{pageNum}")
    public String categoryAttractions(Model model,
                                      @PathVariable(name = "pageNum") int pageNum,
                                      @Param("sortField") String sortField,
                                      @Param("sortDir") String sortDir){

         Page<Place> page = placesService.getPaginatedPlaceListByCategory(pageNum, sortField, sortDir, Category.ATTRACTIONS);
         List<Place> resultList = page.getContent();
         model.addAttribute("currentPage", pageNum);
         model.addAttribute("totalPages", page.getTotalPages());
         model.addAttribute("totalItems", page.getTotalElements());
         model.addAttribute("sortField", sortField);
         model.addAttribute("sortDir", sortDir);
         model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
         model.addAttribute("resultList", resultList);

         return "categoryresultsatt";
    }

    @GetMapping("/categories/lodging/{pageNum}")
    public String categoryLodging(Model model,
                                  @PathVariable(name = "pageNum") int pageNum,
                                  @Param("sortField") String sortField,
                                  @Param("sortDir") String sortDir){
        Page<Place> page = placesService.getPaginatedPlaceListByCategory(pageNum, sortField, sortDir, Category.LODGING);
        List<Place> resultList = page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("resultList", resultList);
        return "categoryresultslg";
    }
    @GetMapping("/categories/food-and-drink/{pageNum}")
    public String categoryFood(Model model,
                               @PathVariable(name = "pageNum") int pageNum,
                               @Param("sortField") String sortField,
                               @Param("sortDir") String sortDir){
        Page<Place> page = placesService.getPaginatedPlaceListByCategory(pageNum, sortField, sortDir, Category.FOODDRINK);
        List<Place> resultList = page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("resultList", resultList);
        return "categoryresultsfd";
    }

}
