package sci.travel_app.walkthebear.web_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sci.travel_app.walkthebear.service.AppUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService appUserService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder= passwordEncoder;
    } */

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       // PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .userDetailsService(appUserService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/css/*","/js/*","/images/*").permitAll()
                .antMatchers("/searchresults").permitAll()
                .antMatchers("/register").permitAll()
                //remove the following after testing:
                .antMatchers("/tripmanager").permitAll()
                .antMatchers("/tripmanager/*").permitAll()
                .antMatchers("/tripmanager/*/*").permitAll()
                .antMatchers("/placemanager").permitAll()
                .antMatchers("/addplace").permitAll()
                .antMatchers("/placedetail/**").permitAll()
                .antMatchers("/places/**").permitAll()
                .antMatchers("/editprofile/").permitAll()
                .antMatchers("/profileinfo").permitAll()
                .antMatchers("/profilefavorites").permitAll()
                .antMatchers("/profileratings").permitAll()
                .antMatchers("/editplaceadmin/{id}").permitAll()
                .antMatchers("/edituseradmin/{id}").permitAll()
                .antMatchers("/deleteplaceadmin/{id}").permitAll()
                .antMatchers("/deleteuseradmin/{id}").permitAll()
                .antMatchers("/profilefavorites").permitAll()
                .antMatchers("/profileratings").permitAll()
                .antMatchers("/adminplace").permitAll()
                .antMatchers("/adminuser").permitAll()
                .antMatchers("/results/**").permitAll()
                .antMatchers("/categories/**").permitAll()
                .antMatchers("/planner/**").permitAll()



                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }

//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }

}

