package in.veritas.repo;
import in.veritas.model.Complaint; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ComplaintRepository extends JpaRepository<Complaint,Long>{
 List<Complaint> findByCitizenUsernameOrderByCreatedAtDesc(String username);
 List<Complaint> findAllByOrderByCreatedAtDesc();
 long countByStatus(String status);
}
