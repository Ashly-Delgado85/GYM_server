package cr.una.gymserver.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity @Table(name="GYM_CLASS")
@Data
public class GymClass {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long clsId;

  @ManyToOne(optional=false) @JoinColumn(name="SRV_ID")
  private ServicePlan service;

  @ManyToOne @JoinColumn(name="TRN_ID")
  private Trainer trainer;

  private String room;
  private Integer capacity;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
}
