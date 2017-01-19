package com.example;

import domain.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class SimpleApplication extends WebSecurityConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(SimpleApplication.class, args);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**", "/events").permitAll()
        .anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

  }

  @RequestMapping("/user")
  public Principal user(OAuth2Authentication principal) {
    System.out.println(principal.getUserAuthentication().getDetails().toString());
    return principal;
  }

  @RequestMapping("/events")
  public List<Event> getEvents() {
    return Event.manyEvents();
  }
}
