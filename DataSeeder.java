package in.veritas.config;
import in.veritas.model.*;import in.veritas.repo.*;import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.*;import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration public class DataSeeder {
 @Bean CommandLineRunner seed(UserRepository users,ComplaintRepository complaints,PasswordEncoder encoder){return args->{
  if(!users.existsByUsername("admin"))users.save(new AppUser("admin",encoder.encode("Admin@123"),"ROLE_ADMIN","VERITAS Administrator"));
  if(!users.existsByUsername("citizen"))users.save(new AppUser("citizen",encoder.encode("Citizen@123"),"ROLE_CITIZEN","Demo Citizen"));
  if(complaints.count()==0){Complaint a=new Complaint("Streetlight not working","Streetlight near the junction has not been working for several days.","Infrastructure","Main junction","citizen");a.setStatus("IN_PROGRESS");a.setAdminRemarks("Forwarded to electrical maintenance.");complaints.save(a);
   complaints.save(new Complaint("Water supply interruption","Water supply has been interrupted since yesterday morning.","Water","Ward 8","citizen"));
   Complaint b=new Complaint("Waste collection delay","Waste collection has not happened this week.","Sanitation","Market road","citizen");b.setStatus("RESOLVED");b.setAdminRemarks("Collection completed.");complaints.save(b);}
 };}
}
