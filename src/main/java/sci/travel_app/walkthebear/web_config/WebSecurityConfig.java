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
                .antMatchers("/home/*").authenticated()
                .antMatchers("/home").permitAll()
                .antMatchers("/", "/index","/home").permitAll()
                .antMatchers("/css/*","/js/*","/images/*").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/placedetail/*").permitAll()
                .antMatchers("/placedetail/*/addtolist").hasAuthority("Traveler")
                .antMatchers("/results/**").permitAll()
                .antMatchers("/categories/**").permitAll()
                .antMatchers("/tripmanager").hasAuthority("Traveler")
                .antMatchers("/tripmanager/*").hasAuthority("Traveler")
                .antMatchers("/tripmanager/*/*").hasAuthority("Traveler")
                .antMatchers("/planner/**").hasAuthority("Traveler")
                .antMatchers("/placemanager").hasAuthority("Host")
                .antMatchers("/addplace").hasAuthority("Host")
                .antMatchers("/editplace/**").hasAuthority("Host")
                .antMatchers("/addphotos/**").hasAnyAuthority("Host", "Admin")
                .antMatchers("/editprofile/**").hasAuthority("Host")
                .antMatchers("/profileinfo").hasAnyAuthority("Host","Traveler")
                .antMatchers("/profilefavorites").hasAuthority("Traveler")
                .antMatchers("/profileratings").hasAuthority("Traveler")
                .antMatchers("/addplaceadmin").hasAuthority("Admin")
                .antMatchers("/profileratings/**").hasAuthority("Traveler")
                .antMatchers("/profilefav/**").hasAuthority("Traveler")
                .antMatchers("/editplaceadmin/{id}").hasAuthority("Admin")
                .antMatchers("/edituseradmin/{id}").hasAuthority("Admin")
                .antMatchers("/deleteplaceadmin/{id}").hasAuthority("Admin")
                .antMatchers("/deleteuseradmin/{id}").hasAuthority("Admin")
                .antMatchers("/adminplace").hasAuthority("Admin")
                .antMatchers("/adminuser").hasAuthority("Admin")
                .antMatchers("/adminallplaces/**").hasAuthority("Admin")
                .antMatchers("/user-images/**").permitAll()
                .antMatchers("/json-files/**").permitAll()
                .antMatchers("/files/**").permitAll()
                .antMatchers("/editratings/**").hasAuthority("Admin")
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

