package sci.travel_app.walkthebear.controller;


import org.apache.logging.log4j.LogManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.service.AppUserServiceImp;

import javax.validation.Valid;

@Controller
public class AppUserController {


    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserServiceImp appUserServiceImp;
    @Autowired
    private ModelMapper modelMapper;

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(AppUserController.class);


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/incorrectLogin")
    public String wrongLogin(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute(new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("appuser") @Valid AppUser appUser,
                                      BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "error";
        }

        appUserServiceImp.save(appUser);
        redirectAttributes.addFlashAttribute("message", "Success");
        return "redirect:/register";

    }

}