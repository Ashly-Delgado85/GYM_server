package cr.una.gymserver.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name="GYM_MEMBER")
@Data
public class Member {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memId;
  @Column(nullable=false, unique=true) private String docId;
  @Column(nullable=false) private String fullName;
  private String phone; private String email;
  @Column(nullable=false) private String status = "ACTIVE";
}
