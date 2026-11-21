package in.veritas.controller;
import in.veritas.model.Complaint; import in.veritas.service.ComplaintService; import org.springframework.web.bind.annotation.*;
import java.security.Principal; import java.util.*;
@RestController @RequestMapping("/api")
public class ApiController {
 private final ComplaintService s; public ApiController(ComplaintService s){this.s=s;}
 @GetMapping("/complaints") public List<Complaint> mine(Principal p){return s.mine(p.getName());}
 @GetMapping("/admin/complaints") public List<Complaint> all(){return s.all();}
 @GetMapping("/admin/analytics") public Map<String,Long> analytics(){return Map.of("total",s.total(),"pending",s.pending(),"inProgress",s.inProgress(),"resolved",s.resolved());}
}
