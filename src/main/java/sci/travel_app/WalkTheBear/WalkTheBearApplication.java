package sci.travel_app.WalkTheBear;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.model.misc.AppUserRole;
import sci.travel_app.WalkTheBear.repository.AppUserRepository;
import sci.travel_app.WalkTheBear.repository.PlacesRepository;
import sci.travel_app.WalkTheBear.service.ItineraryService;
import sci.travel_app.WalkTheBear.service.ScheduleService;

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
//			repository1.save(new AppUser("Rapunzel", "myotherpassword", "someotheremail@someemailprovider.com", AppUserRole.HOST ));
//		};
//	}
@Bean
	public CommandLineRunner demo(ItineraryService itineraryService1, ScheduleService scheduleService1, PlacesRepository placeRepository2) {
	return (args) -> {
		Itinerary trip1 = itineraryService1.createItinerary("Trip1");
		DailySchedule day1 = scheduleService1.addNewDay(trip1);
//		scheduleService1.addToSchedule(placeRepository2.findById(1), day1, "10:00");
	};
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
