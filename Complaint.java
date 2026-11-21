package in.veritas.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="complaints")
public class Complaint {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false,length=3000) private String description;
 private String category; private String location; private String citizenUsername;
 @Column(nullable=false) private String status="PENDING";
 @Column(length=2000) private String adminRemarks;
 private LocalDateTime createdAt=LocalDateTime.now(); private LocalDateTime updatedAt=LocalDateTime.now();
 public Complaint(){}
 public Complaint(String title,String description,String category,String location,String citizenUsername){
  this.title=title;this.description=description;this.category=category;this.location=location;this.citizenUsername=citizenUsername;
 }
 @PreUpdate public void updateTimestamp(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public String getCategory(){return category;} public void setCategory(String v){category=v;}
 public String getLocation(){return location;} public void setLocation(String v){location=v;}
 public String getCitizenUsername(){return citizenUsername;} public void setCitizenUsername(String v){citizenUsername=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public String getAdminRemarks(){return adminRemarks;} public void setAdminRemarks(String v){adminRemarks=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
