package com.example.application_management_system.Service;

import com.example.application_management_system.Entity.Applicant;
import com.example.application_management_system.repository.ApplicantCurdRepository;
import com.example.application_management_system.repository.ApplicantPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ApplicantService {
    @Autowired
    ApplicantCurdRepository applicantCurdRepository;
    @Autowired
    ApplicantPagingAndSortingRepository applicantPagingAndSortingRepository;

    public List<Applicant> getApplicants() {

        Iterable<Applicant> applicants = applicantCurdRepository.findAll();

        return StreamSupport.stream(applicants.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Applicant saveApplicants(Applicant applicant) {
        return applicantCurdRepository.save(applicant);
    }

    public Iterable<Applicant> getApplicantsWithPagination(int page, int size) {
        return applicantPagingAndSortingRepository.findAll(PageRequest.of(page, size));

    }
}
