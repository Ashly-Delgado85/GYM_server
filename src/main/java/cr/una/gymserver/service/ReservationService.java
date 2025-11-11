package cr.una.gymserver.service;

import cr.una.gymserver.model.GymClass;
import cr.una.gymserver.model.Reservation;
import cr.una.gymserver.repository.GymClassRepo;
import cr.una.gymserver.repository.ReservationRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepo repo;
  private final GymClassRepo classRepo;
  private final EntityManager em;

  @Transactional
  public Reservation reserve(Long memId, Long clsId) {
    
    if (repo.existsByMemIdAndClsId(memId, clsId)) {
      throw new IllegalStateException("El miembro ya reservó esta clase");
    }

    
    Number spots = (Number) em.createNativeQuery(
        "SELECT gym_pkg.available_spots(?1) FROM dual")
        .setParameter(1, clsId)
        .getSingleResult();

    if (spots.intValue() <= 0) {
      throw new IllegalStateException("Sin cupo");
    }

    // Crear reserva
    Reservation r = new Reservation();
    r.setMemId(memId);
    r.setClsId(clsId);
    r.setRsvStatus("CONFIRMED");
    return repo.save(r);
  }

  
  public int available(Long clsId) {
    GymClass cls = classRepo.findById(clsId).orElseThrow();
    long taken = repo.countByClsIdAndRsvStatus(clsId, "CONFIRMED");
    return cls.getCapacity() - (int) taken;
  }


  public List<Reservation> findAll() {
    return repo.findAll();
  }

  
  @Transactional
  public void cancel(Long rsvId) {
    Reservation r = repo.findById(rsvId)
        .orElseThrow(() -> new IllegalArgumentException("Reserva no existe"));
    r.setRsvStatus("CANCELLED");
    repo.save(r);
  }

    public int getAvailable(long clsId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
