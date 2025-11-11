package cr.una.gymserver.repository;
import cr.una.gymserver.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MemberRepo extends JpaRepository<Member, Long> {}
