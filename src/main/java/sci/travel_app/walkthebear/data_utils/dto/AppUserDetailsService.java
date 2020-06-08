//package sci.travel_app.walkthebear.data_utils.dto;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import sci.travel_app.walkthebear.model.entities.AppUser;
//import sci.travel_app.walkthebear.repository.AppUserRepository;
//
//import java.util.Date;
//
//
//public class AppUserDetailsService implements UserDetailsService {
//    @Autowired
//    private AppUserRepository appUserRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AppUser user = appUserRepository.findByUserName(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("Could not find user");
//        }
//
//        return new AppUserDetails(user);
//
//    }
//}
