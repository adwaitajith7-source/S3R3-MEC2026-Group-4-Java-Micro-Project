package com.veritas.controller;
import com.veritas.model.Complaint;import com.veritas.repository.ComplaintRepository;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;import java.time.LocalDateTime;import java.util.*;
@RestController @RequestMapping("/api/admin/complaints") public class AdminComplaintController{private final ComplaintRepository repo;public AdminComplaintController(ComplaintRepository r){repo=r;}
 @GetMapping public List<Complaint> all(){return repo.findAll();}
 @PatchMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id,@RequestBody UpdateComplaint u){if(u.status==null||!Set.of("PENDING","IN_PROGRESS","RESOLVED","REJECTED").contains(u.status))return ResponseEntity.badRequest().body(Map.of("error","Invalid status. Use PENDING, IN_PROGRESS, RESOLVED, or REJECTED."));return repo.findById(id).map(c->{c.setStatus(u.status);c.setAdminRemarks(u.remarks);c.setUpdatedAt(LocalDateTime.now());return ResponseEntity.ok(repo.save(c));}).orElse(ResponseEntity.notFound().build());}
 public static class UpdateComplaint{public String status;public String remarks;}}
