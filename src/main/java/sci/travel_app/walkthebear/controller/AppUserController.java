package sci.travel_app.walkthebear.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.service.AppUserServiceImp;

import javax.validation.Valid;

@Controller
public class AppUserController {


    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserServiceImp appUserServiceImp;
    @Autowired
    private ModelMapper modelMapper;

//    private AppUserDTO convertToDto(AppUser appUser) {
//        return modelMapper.map(appUser, AppUserDTO.class);
//    }


    @GetMapping( value = "/login" )
    public String login( Model model ) {
        model.addAttribute( new AppUser() );
        return "login";
    }

    @GetMapping("/incorrectLogin")
    public String wrongLogin(Model model) {

      return "incorrectLogin";
 }
    @GetMapping("/okLoginRegister")
    public String okLogin(Model model) {

        return "okLoginRegister";
    }

    @RequestMapping( value = "/login", method = RequestMethod.POST )
        public String performLogin(
                @ModelAttribute( "appUser" ) AppUser appUser, BindingResult result ) {

            AppUser user = appUserRepository
                    .findByUserNameAndPassword( appUser.getUserName(), appUser.getPassword() );
            if ( user == null ) {
                return "redirect:/incorrectLogin";
            }
            return "redirect:/okLoginRegister";
        }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute( new AppUser() );
        return "register";
    }

   @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid AppUser appUser,
                                      BindingResult result) {

  //      AppUserDTO existingEmail = convertToDto(appUserServiceImp.findByEmail(appUserDTO.getEmail()));
   //     if (existingEmail != null) {
   //         result.rejectValue("email", null, "There is already an account registered with that email");
    //    }

//       AppUserDTO existingUserName = convertToDto(appUserServiceImp.findByUserName(appUserDTO.getUserName()));
//       if (existingUserName != null) {
//           result.rejectValue("userName", null, "There is already an account registered with that userName");
//       }

        if (result.hasErrors()) {
            return "error";
        }

        appUserServiceImp.save(appUser);
        return "redirect:/okLoginRegister";
    }


    }











