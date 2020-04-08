package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sci.travel_app.WalkTheBear.model.entities.Favorite;
import sci.travel_app.WalkTheBear.service.AppUserServiceImp;
import sci.travel_app.WalkTheBear.service.FavoritesServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoritesController {

    @Autowired
    private FavoritesServiceImpl favoritesServiceImpl;

    @Autowired
    private AppUserServiceImp appUserServiceImp;

    @RequestMapping(value = "/profileinfo", method = RequestMethod.GET)
    public String profileinfo(@RequestParam Long id, Model model){
        model.addAttribute("user", appUserServiceImp.findById(id));
        return "profileinfo";
    }

//    @RequestMapping(value = "profilefavorites", method = RequestMethod.GET)
//    public ModelAndView favorites() {
//        ModelAndView mav = new ModelAndView("profilefavorites");
//        mav.addObject("favoritesList", favoritesServiceImpl.getAllFavorites());
//        return mav;
//    }

    @RequestMapping(value = "/profilefavorites", method = RequestMethod.GET)
    public String favoritesById(@RequestParam Long id, Model model) {

        List<Favorite> favoritesByUserId = new ArrayList<>();

        for (Favorite fav : favoritesServiceImpl.getAllFavorites()) {
            if (fav.getUser().getId() == id) {
                favoritesByUserId.add(fav);
            }
        }
        model.addAttribute("favorites", favoritesByUserId);

        return "profilefavorites";
    }

    @GetMapping("/editprofile")
    public String editProfile(){
        return "editprofile";
    }

}