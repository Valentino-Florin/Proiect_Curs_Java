package sci.travel_app.walkthebear;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WalkTheBearApplication {


	public static void main(String[] args) {
		SpringApplication.run(WalkTheBearApplication.class, args);
	}

	//this is used for the AppUser controller
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
