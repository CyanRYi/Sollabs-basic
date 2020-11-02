package tech.sollabs.springsecuritydemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(new NegatedRequestMatcher(new AntPathRequestMatcher("/"))))
        .authorizeRequests(
            requests ->
                requests.antMatchers(HttpMethod.POST, "/api/register").anonymous()
                    .anyRequest().authenticated());
  }
}
