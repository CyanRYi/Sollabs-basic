package tech.sollabs.springsecuritydemo.configuration.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JsonLoginRequestFilter extends UsernamePasswordAuthenticationFilter {

  private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    Map<String, String> loginRequest;

    try {
      loginRequest = OBJECT_MAPPER.readValue(request.getInputStream(),
          new TypeReference<Map<String, String>>() {});
    } catch (IOException e) {
      throw new AuthenticationServiceException("Bad Login Request");
    }

    String email = loginRequest.get("email");
    String password = loginRequest.get("password");

    if (email == null) {
      email = "";
    }

    if (password == null) {
      password = "";
    }

    email = email.trim();

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
        email, password);

    // Allow subclasses to set the "details" property
    setDetails(request, authRequest);

    return getAuthenticationManager().authenticate(authRequest);
  }
}
