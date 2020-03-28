package sci.travel_app.WalkTheBear.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sci.travel_app.WalkTheBear.model.entities.AppUser;

public interface AppUserService extends UserDetailsService {

    void save(AppUser user);

    AppUser findByEmail(String email);
}
