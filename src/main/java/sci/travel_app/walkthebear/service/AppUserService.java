package sci.travel_app.walkthebear.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sci.travel_app.walkthebear.model.entities.AppUser;

public interface AppUserService extends UserDetailsService {

     void save(AppUser user);

    AppUser findByEmail(String email);

    AppUser findByUsername(String userName);
    //List<AppUser> findByUserName(String userName);
}