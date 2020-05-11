package sci.travel_app.WalkTheBear;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.repository.PlacesRepository;
import sci.travel_app.WalkTheBear.service.HourMappingService;
import sci.travel_app.WalkTheBear.service.ItineraryService;
import sci.travel_app.WalkTheBear.service.DailyScheduleService;

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

//@Bean
//	public CommandLineRunner demo(ItineraryService itineraryService1, DailyScheduleService dailyScheduleService1, HourMappingService hourMappingService, PlacesRepository placeRepository2) {
//	return (args) -> {
//		Itinerary trip1 = itineraryService1.createItinerary("Trip1");
//		DailySchedule day1 = dailyScheduleService1.addNewDay(trip1);
//		hourMappingService.createDefaultDay(day1);
//		DailySchedule day2 = dailyScheduleService1.addNewDay(trip1);
//		hourMappingService.createDefaultDay(day2);
//
//	};
//	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
