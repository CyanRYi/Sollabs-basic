package tech.sollabs.springsecuritydemo.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.sollabs.springsecuritydemo.member.Member;
import tech.sollabs.springsecuritydemo.member.repository.MemberRepository;

@Service
public class MemberService {

  private MemberRepository repository;
  private PasswordEncoder passwordEncoder;

  public MemberService(MemberRepository repository,
      PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  public Member createMember(Member member) {
    String encryptedPassword = passwordEncoder.encode(member.getPassword());
    member.setPassword(encryptedPassword);

    return repository.save(member);
  }
}
