package sci.travel_app.walkthebear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sci.travel_app.walkthebear.service.AppUserServiceImp;
import sci.travel_app.walkthebear.service.FavoritesServiceImpl;

@Controller
public class FavoritesController {

    @Autowired
    private FavoritesServiceImpl favoritesServiceImpl;

    @Autowired
    private AppUserServiceImp appUserServiceImp;

   /* @GetMapping("/profileinfo")
    public String profileinfo(Model model, Principal principal) {
        model.addAttribute("user", appUserServiceImp.findByUserName(principal.getName()));
        return "profileinfo";
    }*/

    /*@RequestMapping(value = "/profileinfo", method = RequestMethod.GET)
    public String profileinfo(@RequestParam Long id, Model model){
        model.addAttribute("user", appUserServiceImp.findById(id));
        return "profileinfo";
    }*/

    @RequestMapping(value = "profilefavorites", method = RequestMethod.GET)
    public ModelAndView favorites() {
        ModelAndView mav = new ModelAndView("profilefavorites");
        mav.addObject("favoritesList", favoritesServiceImpl.getAllFavorites());
        return mav;
    }


    ///replace with correct implementation:

//    @RequestMapping(value = "/profilefavorites", method = RequestMethod.GET)
//    public String favoritesById(Model model, Principal principal) {
//
//        principal.getName();
//        List<Favorite> favoritesByUserId = new ArrayList<>();
//
//        for (Favorite fav : favoritesServiceImpl.getAllFavorites()) {
//            if (fav.getUser().getId() == id) {
//                favoritesByUserId.add(fav);
//            }
//        }
//        model.addAttribute("favorites", favoritesByUserId);
//
//        return "profilefavorites";
//    }

   /* @GetMapping("/editprofile")
    public String editProfile() {
        return "editprofile";
    }*/

}