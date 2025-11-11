package cr.una.gymserver.repository;
import cr.una.gymserver.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    long countByClsIdAndRsvStatus(Long clsId, String status);
    boolean existsByMemIdAndClsId(Long memId, Long clsId);
}
