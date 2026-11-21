package com.veritas.model;
import jakarta.persistence.*;import java.time.LocalDateTime;
@Entity @Table(name="complaints") public class Complaint {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=140) private String title;
 @Column(nullable=false,length=3000) private String description;
 @Column(nullable=false,length=100) private String category;
 @Column(nullable=false) private String citizenUsername;
 @Column(nullable=false) private String status="PENDING";
 @Column(length=3000) private String adminRemarks;
 @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
 private LocalDateTime updatedAt=LocalDateTime.now();
 public Complaint(){} public Complaint(String title,String description,String category,String citizenUsername){this.title=title;this.description=description;this.category=category;this.citizenUsername=citizenUsername;}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String s){title=s;} public String getDescription(){return description;} public void setDescription(String s){description=s;} public String getCategory(){return category;} public void setCategory(String s){category=s;} public String getCitizenUsername(){return citizenUsername;} public void setCitizenUsername(String s){citizenUsername=s;} public String getStatus(){return status;} public void setStatus(String s){status=s;} public String getAdminRemarks(){return adminRemarks;} public void setAdminRemarks(String s){adminRemarks=s;} public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime d){updatedAt=d;}
}
