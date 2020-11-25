package tech.sollabs.springsecuritydemo.configuration.security;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class JsonLoginConfigurer<T extends HttpSecurityBuilder<T>>
    extends AbstractAuthenticationFilterConfigurer<T, JsonLoginConfigurer<T>, JsonLoginRequestFilter> {

  public JsonLoginConfigurer() {
    super(new JsonLoginRequestFilter(), "/login");
  }

  @Override
  protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
    return new AntPathRequestMatcher(loginProcessingUrl, "POST");
  }
}
