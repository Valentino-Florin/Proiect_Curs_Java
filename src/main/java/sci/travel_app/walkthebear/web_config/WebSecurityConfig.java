package sci.travel_app.walkthebear.web_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sci.travel_app.walkthebear.service.AppUserService;
import sci.travel_app.walkthebear.service.AppUserServiceImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService appUserService;

    @Bean
    public UserDetailsService appUserDetailsService() {
        return new AppUserServiceImp();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(appUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //available to all users
                .antMatchers("/home/*").permitAll()
                .antMatchers("/", "/index","/home").permitAll()
                .antMatchers("/css/*","/js/*","/images/*").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/placedetail/*").permitAll()
                .antMatchers("/placedetail/*/addtolist").hasAuthority("Traveler")
//                .antMatchers("/searchresults").permitAll()
                .antMatchers("/results/**").permitAll()
                .antMatchers("/categories/**").permitAll()
                //available to role: traveller
                .antMatchers("/tripmanager").permitAll()
//                .antMatchers("/tripmanager").hasAuthority("Traveler")
                .antMatchers("/tripmanager/*").permitAll()
                .antMatchers("/tripmanager/*/*").permitAll()
                .antMatchers("/planner/**").permitAll()
                //available to role: host
                .antMatchers("/placemanager").permitAll()
                .antMatchers("/addplace").permitAll()
                .antMatchers("/editplace/**").permitAll()
                .antMatchers("/addphotos/**").permitAll()
//                .antMatchers("/places/**").permitAll()
                .antMatchers("/editprofile/**").permitAll()
                .antMatchers("/profileinfo").permitAll()
                .antMatchers("/profilefavorites").permitAll()
                .antMatchers("/profileratings").permitAll()
                .antMatchers("/addplaceadmin").permitAll()
                .antMatchers("/profileratings/**").permitAll()
                .antMatchers("/profilefav/**").permitAll()
                .antMatchers("/editplaceadmin/{id}").permitAll()
                .antMatchers("/edituseradmin/{id}").permitAll()
                .antMatchers("/deleteplaceadmin/{id}").permitAll()
                .antMatchers("/deleteuseradmin/{id}").permitAll()
                .antMatchers("/adminplace").permitAll()
                .antMatchers("/adminuser").permitAll()
                .antMatchers("/adminallplaces/**").permitAll()
                .antMatchers("/user-images/**").permitAll()
                .antMatchers("/json-files/**").permitAll()
                .antMatchers("/files/**").permitAll()
//                .antMatchers("/editratings/{id}").permitAll()
                .antMatchers("/deleteratings/{id}").permitAll()
                .antMatchers("/editratings/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
        .defaultSuccessUrl("/home",true)
        .failureUrl("/incorrectLogin")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/doLogout")
                .logoutSuccessUrl("/home")
                .permitAll();

    }

}

