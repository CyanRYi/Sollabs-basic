package tech.sollabs.springsecuritydemo.configuration.security;

import java.util.Collections;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.sollabs.springsecuritydemo.member.repository.MemberRepository;

public class DatabaseUserDetailsService implements UserDetailsService {

  private MemberRepository memberRepository;

  public DatabaseUserDetailsService(
      MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return memberRepository.findByEmailIgnoreCase(username)
        .map(member -> new User(member.getEmail(), member.getPassword(), Collections.emptySet()))
        .orElseThrow(() -> new UsernameNotFoundException("Member cannot Found"));
  }
}
