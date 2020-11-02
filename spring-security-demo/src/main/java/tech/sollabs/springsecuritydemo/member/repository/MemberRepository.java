package tech.sollabs.springsecuritydemo.member.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sollabs.springsecuritydemo.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

  Optional<Member> findByEmailIgnoreCase(String email);
}
