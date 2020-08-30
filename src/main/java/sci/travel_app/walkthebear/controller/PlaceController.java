package sci.travel_app.walkthebear.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.service.PlacesServiceImp;
import sci.travel_app.walkthebear.service.UploadService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
public class PlaceController {

    @Autowired
    private PlacesServiceImp placesService;
    @Autowired
    private UploadService uploadService;
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PlaceController.class);

    /* @PostMapping(value = "/addplace")
      public String addNewPlace( @ModelAttribute Place place )
      {
      placesService.addPlace(place);
          return "redirect:placemanager";
      } */



    @GetMapping("/addplaceadmin")
    public String showNewPlaceFormAdmin(Model model) {
        model.addAttribute("place", new Place());
        return "addplaceadmin";
    }

    @PostMapping("/addplaceadmin")
    public String addNewPlaceAdmin(@Valid Place place, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addplaceadmin";
        }

        placesService.addPlace(place);
        model.addAttribute("place", placesService.getAllPlaces());
        return "redirect:adminplace";
    }
    @GetMapping("/addplace")
    public String showNewPlaceForm(Model model) {
        model.addAttribute("place", new Place());
        return "addplace";
    }

    @PostMapping("/addplace")
    public String addNewPlace(@Valid Place place, BindingResult result, Model model, RedirectAttributes redirectAttributes,
                              @RequestParam("thumbnail") MultipartFile multipartFile,
                              @RequestParam("galleryImg") MultipartFile[] galleryImageFiles) throws IOException {
        if (result.hasErrors()) {
            return "addplace";
        }
        String fileNameT = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        place.setThumbnailPath(fileNameT);
        int count = 0;
        for (MultipartFile galleryImage : galleryImageFiles) {
            if (galleryImage != null){
            String fileNameG = StringUtils.cleanPath(galleryImage.getOriginalFilename());
            if (count == 0) place.setGalleryImage1(fileNameG);
            if (count == 1) place.setGalleryImage2(fileNameG);
            if (count == 2) place.setGalleryImage3(fileNameG);
            if (count == 3) place.setGalleryImage4(fileNameG);
            if (count == 4) place.setGalleryImage5(fileNameG);
            count++; }
        }
        Place savedPlace = placesService.addPlace(place);
        uploadService.uploadThumbnailFile(savedPlace, multipartFile, fileNameT);
        for (MultipartFile galleryImage : galleryImageFiles) {
            if (galleryImage != null){
            String fileNameG = StringUtils.cleanPath(galleryImage.getOriginalFilename());
            uploadService.uploadGalleryImageFile(savedPlace, galleryImage, fileNameG);}
            }

        model.addAttribute("place", placesService.getAllPlaces());
        redirectAttributes.addFlashAttribute("place", place);
        redirectAttributes.addFlashAttribute("message", "Place saved!");
        return "redirect:placemanager";
    }
     /*@GetMapping("/adminplace")000000000000
     public String showAdminPlace(Model model, String placeName) {
         model.addAttribute("placeSearch", placesService.getPlaceByName(placeName));
         return "adminplace";
     } */
    @GetMapping("/adminplace")
    public String showAdminPlace(@RequestParam (value = "placeSearch", required = false) String placeName, Model model) {
        model.addAttribute("placeSearch", placesService.getPlaceByName(placeName));
        return "adminplace";
    }
    /* @RequestMapping("/adminplace")
     public ModelAndView search(@RequestParam String keyword) {
         List<Place> result = placesService.search(keyword);
         ModelAndView mav = new ModelAndView("search");
         mav.addObject("result", result);
         return mav;
     } */

    //not used
//    @GetMapping("/category")
//    public String showPlacesByCategory(Model model, Category category) {
//        model.addAttribute("category", placesService.getPlaceByCategory(category));
//        return "categoryresults";
//    }
    @GetMapping("/editplaceadmin/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Place place = placesService.getPlaceById(id);
        // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("place", place);
        return "editplaceadmin";
    }
    @PostMapping("/editplaceadmin/{id}")
    public String changePlace(@PathVariable("id") long id, @Valid Place place,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            place.setId(id);
            return "editplaceadmin";
        }

        placesService.updatePlace(place);
        model.addAttribute("place", placesService.getAllPlaces());
        return "adminplace";
    }

    @GetMapping("/deleteplaceadmin/{id}")
    public String erasePlace(@PathVariable("id") long id, Model model) throws IllegalArgumentException {
        Place place = placesService.getPlaceById(id);
        //   .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        placesService.deletePlace(id);
        model.addAttribute("place", placesService.getAllPlaces());
        return "adminplace";
    }

}