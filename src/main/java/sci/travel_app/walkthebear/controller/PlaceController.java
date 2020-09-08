package sci.travel_app.walkthebear.controller;

import org.apache.logging.log4j.Level;
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
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.service.AppUserServiceImp;
import sci.travel_app.walkthebear.service.PlacesServiceImp;
import sci.travel_app.walkthebear.data_utils.UploadService;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;


@Controller
public class PlaceController {

    @Autowired
    private PlacesServiceImp placesService;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserServiceImp appUserServiceImp;

    @Autowired
    private UploadService uploadService;
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PlaceController.class);


    /**
     * Method used to populate "/placemanager" with the places specific to a logged user
     *
     * @param model
     * @param principal
     * @return "placemanager"
     */

    @GetMapping("/placemanager")
    public String showPlaceManager(Model model, Principal principal) {
        model.addAttribute("userPlaces", placesService.findPlaceByUser(appUserServiceImp.findByUserName(principal.getName())));
        return "placemanager";
    }

    @GetMapping("/addplace")
    public String showNewPlaceForm(Model model) {
        model.addAttribute("place", new Place());
        return "addplace";
    }

    @PostMapping("/addplace")
    public String addNewPlace(@Valid Place place, BindingResult result, Model model, Principal principal, RedirectAttributes redirectAttributes)
                              {
        if (result.hasErrors()) {
            return "addplace";
        }
        Place savedPlace = placesService.addPlace(place);

        placesService.addUserPlace(place, appUserRepository.findByUserName(principal.getName()));
        model.addAttribute("place", placesService.getAllPlaces());
        redirectAttributes.addFlashAttribute("message", "Place saved!");
        logger.log(Level.INFO, "Place added : " + place);
        return "redirect:placemanager";
    }

    @GetMapping("/editplace/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Place place = placesService.getPlaceById(id);
        // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("place", place);
        return "editplace";
    }

    @PostMapping("/editplace/{id}")
    public String editPlace(@PathVariable("id") long id, @Valid Place place,
                            BindingResult result, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            place.setId(id);
            return "editplace";
        }

        placesService.updateUserPlace(place, appUserServiceImp.findByUserName(principal.getName()));
        model.addAttribute("userPlaces", placesService.findPlaceByUser(appUserServiceImp.findByUserName(principal.getName())));
        redirectAttributes.addFlashAttribute("message", "Place was updated");
        logger.log(Level.INFO, "Updated place: ID " + id);
        return "placemanager";
    }















    @GetMapping("/addphotos/{id}")
    public String showPhotosForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("place", placesService.getPlaceById(id));
        model.addAttribute("hasThumbnail", placesService.hasPic(placesService.getPlaceById(id).getThumbnailFileName()));
        model.addAttribute("hasG1", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage1FileName()));
        model.addAttribute("hasG2", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage2FileName()));
        model.addAttribute("hasG3", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage3FileName()));
        model.addAttribute("hasG4", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage4FileName()));
        model.addAttribute("hasG5", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage5FileName()));
        return "photos";
    }

    @PostMapping("/addphotos/{id}")
    public String addPhotos(@PathVariable("id") long id, @Valid Place place, BindingResult result, Model model, RedirectAttributes redirectAttributes,
                              @RequestParam(value ="thumbnail", required = false) MultipartFile multipartFile,
                              @RequestParam(value = "galleryImage1", required = false) MultipartFile galleryImageFiles1,
                            @RequestParam(value = "galleryImage2", required = false) MultipartFile galleryImageFiles2,
                            @RequestParam(value = "galleryImage3", required = false) MultipartFile galleryImageFiles3,
                            @RequestParam(value = "galleryImage4", required = false) MultipartFile galleryImageFiles4,
                            @RequestParam(value = "galleryImage5", required = false) MultipartFile galleryImageFiles5) throws IOException {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Could not update");
            return "photos/{id}";
        }
        Place savedPlace = placesService.getPlaceById(id);
        String fileNameT = multipartFile.getOriginalFilename();
        if (!"".equals(fileNameT)) {
            uploadService.uploadImageFile(savedPlace, multipartFile, StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        }
        String fileNameG1 = galleryImageFiles1.getOriginalFilename();
        if (!"".equals(fileNameG1)){
        uploadService.uploadImageFile(savedPlace, galleryImageFiles1, StringUtils.cleanPath(galleryImageFiles1.getOriginalFilename()));
        }
        String fileNameG2 = galleryImageFiles2.getOriginalFilename();
        if (!"".equals(fileNameG2)) {
            uploadService.uploadImageFile(savedPlace, galleryImageFiles2, StringUtils.cleanPath(galleryImageFiles2.getOriginalFilename()));
        }
        String fileNameG3 = galleryImageFiles3.getOriginalFilename();
        if (!"".equals(fileNameG3)) {
            uploadService.uploadImageFile(savedPlace, galleryImageFiles3, StringUtils.cleanPath(galleryImageFiles3.getOriginalFilename()));
        }
        String fileNameG4 = galleryImageFiles4.getOriginalFilename();
        if (!"".equals(fileNameG4)) {
            uploadService.uploadImageFile(savedPlace, galleryImageFiles4, StringUtils.cleanPath(galleryImageFiles4.getOriginalFilename()));
        }
        String fileNameG5 = galleryImageFiles5.getOriginalFilename();
        if (!"".equals(fileNameG5)) {
            uploadService.uploadImageFile(savedPlace, galleryImageFiles5, StringUtils.cleanPath(galleryImageFiles5.getOriginalFilename()));
        }

        placesService.updatePhotos(savedPlace, fileNameT, fileNameG1, fileNameG2, fileNameG3, fileNameG4, fileNameG5);

        model.addAttribute("place", savedPlace);

        redirectAttributes.addFlashAttribute("message", "Photos saved!");
        logger.log(Level.INFO, "Photos added to : " + place );
        return "redirect:/addphotos/{id}";
    }

























    /**
     * Method used to delete a place, specific to a logged user, by using the ID
     *
     * @param id                 - place ID
     * @param model
     * @param redirectAttributes
     * @param principal
     * @return "placemanager"
     * @throws IllegalArgumentException
     */
    @GetMapping("/deleteplace/{id}")
    public String erasePlace(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes, Principal principal) throws IllegalArgumentException {
        Place place = placesService.getPlaceById(id);
        //   .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        placesService.deletePlace(id);
        model.addAttribute("userPlaces", placesService.findPlaceByUser(appUserServiceImp.findByUserName(principal.getName())));
        redirectAttributes.addFlashAttribute("message", "Place was deleted");
        logger.log(Level.INFO, "Deleted place: ID " + id);
        return "placemanager";
    }

}