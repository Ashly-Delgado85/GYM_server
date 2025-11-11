package cr.una.gymserver.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name="GYM_SERVICE")
@Data
public class ServicePlan {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long srvId;
  @Column(nullable=false) private String name;
  private String description;
  @Column(nullable=false) private Double price;
}
