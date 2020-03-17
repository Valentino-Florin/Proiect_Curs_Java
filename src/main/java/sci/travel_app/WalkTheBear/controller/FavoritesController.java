package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sci.travel_app.WalkTheBear.model.entities.Favorite;
import sci.travel_app.WalkTheBear.service.FavoritesServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoritesController {

    @Autowired
    private FavoritesServiceImpl favoritesServiceImpl;

    @RequestMapping(value = "/profileinfo", method = RequestMethod.GET)
    public String profileinfo(){
        return "profileinfo";
    }

//    @RequestMapping(value = "profilefavorites", method = RequestMethod.GET)
//    public ModelAndView favorites() {
//        ModelAndView mav = new ModelAndView("profilefavorites");
//        mav.addObject("favoritesList", favoritesServiceImpl.getAllFavorites());
//        return mav;
//    }

    @RequestMapping(value = "/profilefavorites", method = RequestMethod.GET)
    public ModelAndView favoritesById(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("profilefavorites");
        List<Favorite> favoritesByUserId = new ArrayList<>();

        for (Favorite fav : favoritesServiceImpl.getAllFavorites()) {
            if (fav.getUser().getId() == id) {
                favoritesByUserId.add(fav);
            }
        }
        mav.addObject("favoritesList", favoritesByUserId);
        return mav;
    }

    @GetMapping("/editprofile")
    public void editProfile(){

    }

}
