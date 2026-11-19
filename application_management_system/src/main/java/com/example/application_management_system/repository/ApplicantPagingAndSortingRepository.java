package com.example.application_management_system.repository;

import com.example.application_management_system.Entity.Applicant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicantPagingAndSortingRepository extends PagingAndSortingRepository<Applicant, Long> {

}
