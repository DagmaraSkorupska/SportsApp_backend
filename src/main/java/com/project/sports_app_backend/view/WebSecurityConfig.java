package com.project.sports_app_backend.view;

import com.project.sports_app_backend.domain.UserEntity;
import com.project.sports_app_backend.domain.UserType;
import com.project.sports_app_backend.repository.UserRepository;
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

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
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
        UserEntity userEntityUser = new UserEntity(UserType.USER, "Jan", "Nowak", "JN@test.pl", passwordEncoder().encode("JN@test.pl"),  "abc", "132365", null, null);
        userRepository.save(userEntityUser);
    }
}
