package sci.travel_app.walkthebear.controller;


import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sci.travel_app.walkthebear.data_utils.FileService;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.service.PlacesServiceImp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {
    @Autowired
    private PlacesServiceImp placeService;
    @Autowired
    private FileService fileService;

    @GetMapping(value = "/")
    public String slashRedirect(){
        return "redirect:/home";
    }
    @GetMapping(value = "/index")
    public String indexRedirect(){
        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String home( Model model) {
        List<Place> widget1 = new ArrayList<>();
        List<Place> mostPopularPlaces = placeService.mostPopularPlaces();
        widget1.add(mostPopularPlaces.get(0));
        widget1.add(mostPopularPlaces.get(1));
        widget1.add(mostPopularPlaces.get(2));
        widget1.add(mostPopularPlaces.get(3));
        widget1.add(mostPopularPlaces.get(4));
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


    @GetMapping(value = "/home/mostpopulardownload")
    @ResponseBody
    public ResponseEntity<Resource> mostPopularList() throws FileNotFoundException, DocumentException {

        String file = "mostPopularList.pdf";
        String filepath =  "src/main/resources/static/files/pdf/" + file;

        fileService.createPdfList(placeService.mostPopularPlaces(), file, "Most popular places");

            InputStreamResource resource = new InputStreamResource(new FileInputStream(filepath));
            HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);


    }

    @GetMapping(value = "/home/latestdownload")
    @ResponseBody
    public ResponseEntity<Resource> latestPlacesList() throws FileNotFoundException, DocumentException {

        String file = "latestPlacesList.pdf";
        String filepath =  "src/main/resources/static/files/pdf/" + file;

        fileService.createPdfList(placeService.latestPlaces(), file, "Our newest places");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(filepath));
        HttpHeaders headers = new HttpHeaders(); headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }
}
