package in.veritas.model;
import jakarta.persistence.*;
@Entity @Table(name="app_users")
public class AppUser {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(unique=true, nullable=false) private String username;
 @Column(nullable=false) private String password;
 @Column(nullable=false) private String role;
 private String fullName;
 public AppUser() {}
 public AppUser(String username,String password,String role,String fullName){this.username=username;this.password=password;this.role=role;this.fullName=fullName;}
 public Long getId(){return id;} public String getUsername(){return username;} public void setUsername(String v){username=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public String getRole(){return role;} public void setRole(String v){role=v;}
 public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
}
