package in.veritas.service;
import in.veritas.model.Complaint; import in.veritas.repo.ComplaintRepository; import org.springframework.stereotype.Service;
import java.util.List; import java.util.Optional;
@Service public class ComplaintService {
 private final ComplaintRepository repo; public ComplaintService(ComplaintRepository r){repo=r;}
 public Complaint create(Complaint c){return repo.save(c);}
 public List<Complaint> all(){return repo.findAllByOrderByCreatedAtDesc();}
 public List<Complaint> mine(String user){return repo.findByCitizenUsernameOrderByCreatedAtDesc(user);}
 public Optional<Complaint> find(Long id){return repo.findById(id);}
 public long total(){return repo.count();} public long pending(){return repo.countByStatus("PENDING");}
 public long inProgress(){return repo.countByStatus("IN_PROGRESS");} public long resolved(){return repo.countByStatus("RESOLVED");}
}
