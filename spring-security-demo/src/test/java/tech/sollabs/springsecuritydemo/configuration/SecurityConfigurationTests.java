package tech.sollabs.springsecuritydemo.configuration;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
public class SecurityConfigurationTests {

  private MockMvc mockMvc;

  @BeforeEach
  public void setUp(@Autowired WebApplicationContext applicationContext) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
        .apply(springSecurity())
        .alwaysDo(print())
        .build();
  }

  @Test
  @Sql("classpath:sql/member.sql")
  public void login_success() throws Exception {
    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content("{\"email\": \"test@sollabs.tech\", \"password\": \"test!234\"}"))
        .andExpect(status().isOk());
  }

  @Test
  public void login_badCredential() throws Exception {
    mockMvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content("{\"email\": \"invalid@sollabs.tech\", \"password\": \"test!234\"}"))
        .andExpect(status().isUnauthorized());
  }
}
