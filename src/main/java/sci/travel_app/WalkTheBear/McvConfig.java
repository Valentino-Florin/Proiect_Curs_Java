<<<<<<< Updated upstream
<<<<<<< Updated upstream
package sci.travel_app.WalkTheBear;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class McvConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/searchresults").setViewName("searchresults");
        registry.addViewController("/site").setViewName("site");
        registry.addViewController("/login");
        registry.addViewController("/placedetail");


    }
}
=======
=======
>>>>>>> Stashed changes
package sci.travel_app.WalkTheBear;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class McvConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/searchresults").setViewName("searchresults");
        registry.addViewController("/site").setViewName("site");
        registry.addViewController("/login");
        registry.addViewController("/placedetail");


    }
}
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
