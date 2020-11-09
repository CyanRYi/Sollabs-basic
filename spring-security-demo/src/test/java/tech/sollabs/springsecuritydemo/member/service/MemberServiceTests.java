package tech.sollabs.springsecuritydemo.member.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import tech.sollabs.springsecuritydemo.member.Member;

@SpringBootTest
@ActiveProfiles("test")
public class MemberServiceTests {

  @Autowired
  private MemberService memberService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void createMember_success() {
    String plainPassword = "test!234";
    Member member = new Member();
    member.setEmail("test@sollabs.tech");
    member.setPassword(plainPassword);

    Member result = memberService.createMember(member);

    assertThat(result.getPassword(), is(not(plainPassword)));
    assertThat(passwordEncoder.matches(plainPassword, result.getPassword()), is(true));
  }
}
