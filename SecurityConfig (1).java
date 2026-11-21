package in.veritas.config;
import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;\nimport org.springframework.security.core.userdetails.UserDetailsService;\nimport org.springframework.security.core.userdetails.UsernameNotFoundException;\nimport in.veritas.repo.UserRepository;
@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(UserRepository users){
  return username->users.findByUsername(username)
   .map(u->org.springframework.security.core.userdetails.User.withUsername(u.getUsername())
    .password(u.getPassword()).authorities(u.getRole()).build())
   .orElseThrow(()->new UsernameNotFoundException("User not found"));
 }
 @Bean SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
  http.authorizeHttpRequests(a->a.requestMatchers("/","/login","/register","/css/**","/js/**","/assets/**","/h2-console/**").permitAll()
   .requestMatchers("/admin/**","/api/admin/**").hasRole("ADMIN").requestMatchers("/citizen/**","/api/complaints/**").hasRole("CITIZEN").anyRequest().authenticated())
   .formLogin(f->f.loginPage("/login").defaultSuccessUrl("/dashboard",true).permitAll())
   .logout(l->l.logoutSuccessUrl("/login?logout").permitAll())
   .csrf(c->c.ignoringRequestMatchers("/h2-console/**"))
   .headers(h->h.frameOptions(f->f.sameOrigin()));
  return http.build();
 }
}
