package sci.travel_app.WalkTheBear;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.Lodging;
import sci.travel_app.WalkTheBear.model.misc.AppUserRole;
import sci.travel_app.WalkTheBear.model.misc.LodgingSubCategory;
import sci.travel_app.WalkTheBear.repository.AppUserRepository;
import sci.travel_app.WalkTheBear.repository.PlacesRepository;

@SpringBootApplication
public class WalkTheBearApplication {


	public static void main(String[] args) {
		SpringApplication.run(WalkTheBearApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(AppUserRepository repository1, PlacesRepository repository2) {
//		return (args) -> {
//			repository1.deleteAll();
//			repository1.save(new AppUser("Eugene", "mypassword", "someemail@someemailprovider.com", AppUserRole.TRAVELER ));
//			//repository1.save(new AppUser("Rapunzel", "myotherpassword", "someotheremail@someemailprovider.com", AppUserRole.HOST ));
//			repository2.save(new Lodging("Loc","Judet","Oras", "Adresa", "Numar", "Email", "descriere", repository1.findById(2), LodgingSubCategory.HOTEL ));
//		};
//	}


}
