package com.example.application_management_system.Controller;

import com.example.application_management_system.Entity.Applicant;
import com.example.application_management_system.Service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Applicant>> getApplicants() {

        List<Applicant> applicants = applicantService.getApplicants();

        return ResponseEntity.ok(applicants);
    }

    @PostMapping("/saveDetails")
    public ResponseEntity<Applicant> saveApplicant(
            @RequestBody Applicant applicant) {

        System.out.println("Applicant details: " + applicant);

        Applicant savedApplicant = applicantService.saveApplicants(applicant);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedApplicant);
    }

    @GetMapping("/page")
    public Iterable<Applicant> getApplicantsPage(@RequestParam int page, @RequestParam int size) {
        return applicantService.getApplicantsWithPagination(page, size);
    }
}
