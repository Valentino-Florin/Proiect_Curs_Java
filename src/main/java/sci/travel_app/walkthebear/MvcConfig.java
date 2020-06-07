package sci.travel_app.walkthebear;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/searchresults").setViewName("searchresults");
        registry.addViewController("/tripmanager").setViewName("tripmanager");
        registry.addViewController("/placemanager").setViewName("placemanager");
        registry.addViewController("/placedetail").setViewName("placedetail");
        registry.addViewController("/placedetail/").setViewName("placedetail");
        registry.addViewController("/addplace").setViewName("addplace");
        registry.addViewController("/addplaceadmin").setViewName("addplaceadmin");
        registry.addViewController("/planner").setViewName("planner");
        registry.addViewController("/placedetail").setViewName("placedetail");
        registry.addViewController("/site").setViewName("site");
        registry.addViewController("/editprofile").setViewName("editprofile");
        registry.addViewController("/profileinfo").setViewName("profileinfo");
        registry.addViewController("/profilefavorites").setViewName("profilefavorites");
        registry.addViewController("/profileratings").setViewName("profileratings");
        registry.addViewController("/adminplace").setViewName("adminplace");
        registry.addViewController("/editplaceadmin/{id}").setViewName("editplaceadmin");
        registry.addViewController("/deleteplaceadmin/{id}").setViewName("deleteplaceadmin");
        registry.addViewController("/adminuser").setViewName("adminuser");
        registry.addViewController("/edituseradmin/{id}").setViewName("edituseradmin");
        registry.addViewController("/deleteplaceadmin/{id}").setViewName("deleteplaceadmin");
        registry.addViewController("/adminallplaces").setViewName("adminallplaces");
    }

//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/resources/**")
//                .addResourceLocations("/resources/");
//    }
}
