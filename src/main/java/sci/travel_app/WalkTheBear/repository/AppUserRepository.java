package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.model.entities.AppUser;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findByUserName(String userName);

    AppUser findById(long id);
    AppUser findByEmail(String email);

}