package tech.sollabs.springsecuritydemo.member;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class Member {

  @Id
  @GeneratedValue
  @Type(type = "uuid-char")
  private UUID id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, length = 60)
  private String password;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Member member = (Member) o;
    return Objects.equals(id, member.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
