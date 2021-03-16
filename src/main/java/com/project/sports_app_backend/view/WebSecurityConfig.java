package com.project.sports_app_backend.view;

import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.UserType;
import com.project.sports_app_backend.domain.WorkoutEntity;
import com.project.sports_app_backend.repository.UserRepository;
import com.project.sports_app_backend.repository.WorkoutRepository;
import com.project.sports_app_backend.view.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;
    private WorkoutRepository workoutRepository;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepository userRepository, WorkoutRepository workoutRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/hello").hasAuthority(String.valueOf(UserType.USER))
        .and().formLogin()
//                    .usernameParameter("email")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/default")
//                    .failureUrl("/login?error").permitAll()
       .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).permitAll()
       .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get(){
        UserEntity userEntityUser = new UserEntity(UserType.USER, "Jan", "Nowak", "JN@test.pl", passwordEncoder().encode("JN@test.pl"),  "abc", "132365");
        UserEntity userEntityUser1 = new UserEntity(UserType.USER, "Bogdan", "Kowalski", "BK@test.pl", passwordEncoder().encode("BK@test.pl"),  "def", "789465");
        userRepository.save(userEntityUser);
        userRepository.save(userEntityUser1);

        WorkoutEntity workoutEntity1 = new WorkoutEntity("Run in the forest", "Learn run", 60, 50, "ul.Kozia 32", "Monday, Saturday", "10:00", userEntityUser);
        WorkoutEntity workoutEntity2 = new WorkoutEntity("Swim preper to triathlon", "Learn croul", 60, 100, "ul.Wroclawska 2", "Sunday", "17:00", userEntityUser1);
        WorkoutEntity workoutEntity3 = new WorkoutEntity("Pull up", "Learn pull up", 60, 80, "ul.Ruslka 5", "Saturday", "20:00", userEntityUser);
        workoutRepository.save(workoutEntity1);
        workoutRepository.save(workoutEntity2);
        workoutRepository.save(workoutEntity3);



    }

}

//todo kto moze sie rejsetrwoac jako admin?? czy tylko z poziomu BD czy tez jakos z view
//admin = dodoaje/edyttuje/usuwa uzytkownikow i workouty, rezerwacje
//couch = dodaje workouty, usuwa tylko swoje workouty
//user = dodoaje rezerwacje