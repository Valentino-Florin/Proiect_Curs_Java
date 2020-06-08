package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.data_utils.dto.AppUserDetails;
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

//    @Override
//    public AppUser findByUsername(String userName) {
//        return null;
//    }

    /////don't know what's up with this method
//    @Override
//    public AppUser findByUsername(String userName) {
//        return null;
//    }

//
//    private Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (AppUserRole role : AppUserRole.values()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
//        }
//        return grantedAuthorities;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        AppUser user = appUserRepository.findByEmail(s);
//        if (user == null) {
//            throw new UsernameNotFoundException("Invalid username or password");
//        }
//        return new AppUser(user.getEmail(), passwordEncoder.encode(user.getPassword()), getAuthorities());
//    }
//
    public AppUser findById(Long id) {
        AppUser user = appUserRepository.findById(id).get();
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new AppUserDetails(user);

    }
//    @Override
//    public AppUser findByUserName(String username) throws UsernameNotFoundException {
//        AppUser appUser = (AppUser) appUserRepository.findByUserName(username);
//        if (appUser == null) {
//            throw new UsernameNotFoundException("Invalid username or password");
//        }
//        return appUser;
//    }

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


}