package tech.sollabs.springsecuritydemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import tech.sollabs.springsecuritydemo.configuration.security.AuthenticationHandler;
import tech.sollabs.springsecuritydemo.configuration.security.DatabaseUserDetailsService;
import tech.sollabs.springsecuritydemo.configuration.security.JsonLoginConfigurer;
import tech.sollabs.springsecuritydemo.member.repository.MemberRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private AuthenticationHandler authenticationHandler = new AuthenticationHandler();

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(new NegatedRequestMatcher(new AntPathRequestMatcher("/"))))
        .authorizeRequests(
            requests ->
                requests.antMatchers(HttpMethod.POST, "/api/register").anonymous()
                    .anyRequest().authenticated())
        .apply(new JsonLoginConfigurer<>())
        .successHandler(authenticationHandler)
        .failureHandler(authenticationHandler);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(MemberRepository memberRepository) {
    return new DatabaseUserDetailsService(memberRepository);
  }
}
