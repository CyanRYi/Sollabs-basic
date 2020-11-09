package tech.sollabs.springsecuritydemo.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.sollabs.springsecuritydemo.member.Member;
import tech.sollabs.springsecuritydemo.member.service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {

  private MemberService service;

  public MemberController(MemberService service) {
    this.service = service;
  }

  @GetMapping("/current")
  public Member getCurrentMember() {
    return null;  // 이후에 구현
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerMember(@RequestBody Member member) {
    service.createMember(member);
  }
}
