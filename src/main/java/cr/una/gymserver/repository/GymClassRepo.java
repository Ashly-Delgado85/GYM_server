package cr.una.gymserver.repository;
import cr.una.gymserver.model.GymClass;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GymClassRepo extends JpaRepository<GymClass, Long> {}
