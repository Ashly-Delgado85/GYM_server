package cr.una.gymserver.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name="GYM_TRAINER")
@Data
public class Trainer {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long trnId;
  @Column(nullable=false) private String fullName;
  private String specialty;
  private String email;
}
