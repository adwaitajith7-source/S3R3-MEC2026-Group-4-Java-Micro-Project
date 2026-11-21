package in.veritas.controller;
import in.veritas.model.Complaint; import in.veritas.service.ComplaintService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
import java.security.Principal; import java.util.*; import org.springframework.security.core.Authentication;
@Controller public class WebController {
 private final ComplaintService service; public WebController(ComplaintService s){service=s;}
 @GetMapping("/") public String home(){return "index";}
 @GetMapping("/login") public String login(){return "login";}
 @GetMapping("/register") public String register(){return "register";}
 @GetMapping("/dashboard") public String dashboard(Principal p){
  if(p==null)return "redirect:/login"; // role routing determined via username role stored by security
  return "redirect:/citizen/dashboard";
 }
 @GetMapping("/citizen/dashboard") public String citizen(Model m,Principal p){
  List<Complaint> c=service.mine(p.getName()); m.addAttribute("complaints",c);m.addAttribute("total",c.size());
  m.addAttribute("pending",c.stream().filter(x->"PENDING".equals(x.getStatus())).count());
  m.addAttribute("resolved",c.stream().filter(x->"RESOLVED".equals(x.getStatus())).count());m.addAttribute("recent",c.stream().limit(5).toList());
  return "citizen";
 }
 @GetMapping("/citizen/complaints/new") public String newComplaint(){return "new-complaint";}
 @PostMapping("/citizen/complaints") public String submit(@RequestParam String title,@RequestParam String category,@RequestParam String location,@RequestParam String description,Principal p){
  service.create(new Complaint(title,description,category,location,p.getName())); return "redirect:/citizen/dashboard?submitted";
 }
 @GetMapping("/citizen/complaints") public String myComplaints(Model m,Principal p){m.addAttribute("complaints",service.mine(p.getName()));return "complaints";}
 @GetMapping("/citizen/complaints/{id}") public String detail(@PathVariable Long id,Model m,Principal p){
  Complaint c=service.find(id).orElseThrow(); if(!c.getCitizenUsername().equals(p.getName()))return "redirect:/citizen/complaints";
  m.addAttribute("complaint",c);return "detail";
 }
 @GetMapping("/admin/dashboard") public String admin(Model m){
  m.addAttribute("complaints",service.all());m.addAttribute("total",service.total());m.addAttribute("pending",service.pending());
  m.addAttribute("inProgress",service.inProgress());m.addAttribute("resolved",service.resolved());return "admin";
 }
 @PostMapping("/admin/complaints/{id}/update") public String update(@PathVariable Long id,@RequestParam String status,@RequestParam(required=false) String remarks){
  Complaint c=service.find(id).orElseThrow(); c.setStatus(status);c.setAdminRemarks(remarks);service.create(c);return "redirect:/admin/dashboard";
 }
}
