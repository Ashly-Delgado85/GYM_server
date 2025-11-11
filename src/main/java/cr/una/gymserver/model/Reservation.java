package cr.una.gymserver.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name="GYM_RESERVATION")
@Data
public class Reservation {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rsvId;
  @Column(nullable=false) private Long memId;
  @Column(nullable=false) private Long clsId;
  @Column(nullable=false) private String rsvStatus = "CONFIRMED";
}
