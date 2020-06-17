package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.data_utils.AppUserDetails;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.repository.AppUserRepository;

import java.util.Date;

@Service
public class AppUserServiceImp implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public AppUser save(AppUser user) {
        user.setCreated(new Date());
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);

        return appUserRepository.save(user);
    }

    @Override
    public AppUser findByEmail(String s) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(s);
        if (appUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return appUser;
    }

    public AppUser findById(long id) {
        return appUserRepository.findById(id);
    }

    public AppUser findByUserName(String username) {
        return appUserRepository.findByUserName(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new AppUserDetails(user);

    }

}