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
import java.util.List;

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

    public AppUser saveNewPassoword(AppUser user) {
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);

        return appUserRepository.save(user);
    }

    @Override
    public void update(AppUser user, long id){
        AppUser updatedUser = new AppUser();
        updatedUser.setId(id);
        updatedUser.setUserName(user.getUserName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setCreated(appUserRepository.findById(id).getCreated());
        updatedUser.setRole(appUserRepository.findById(id).getRole());
        updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));

        appUserRepository.save(updatedUser);
    }


    @Override
    public AppUser findByEmail(String s) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(s);
        if (appUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return appUser;
    }
    @Override
    public AppUser findById(long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppUser> findUsersByKeyword(String keyword) {
        return appUserRepository.findUsersByKeyword(keyword);
    }

    @Override
    public void deleteUser(AppUser user) { appUserRepository.delete(user); }


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