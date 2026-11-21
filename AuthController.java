package in.veritas.controller;
import in.veritas.model.AppUser; import in.veritas.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Controller; import org.springframework.web.bind.annotation.*;
@Controller public class AuthController {
 private final UserRepository repo; private final PasswordEncoder encoder;
 public AuthController(UserRepository r,PasswordEncoder e){repo=r;encoder=e;}
 @PostMapping("/register") public String register(@RequestParam String fullName,@RequestParam String username,@RequestParam String password){
  if(repo.existsByUsername(username))return "redirect:/register?exists";
  repo.save(new AppUser(username,encoder.encode(password),"ROLE_CITIZEN",fullName));return "redirect:/login?registered";
 }
}
