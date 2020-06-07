package sci.travel_app.walkthebear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.walkthebear.model.entities.AppUser;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findByUserName(String userName);
    List<AppUser> findAll();
    AppUser findById(long id);
    AppUser findByEmail(String email);
    AppUser findByUserNameAndPassword(String userName, String password);

}