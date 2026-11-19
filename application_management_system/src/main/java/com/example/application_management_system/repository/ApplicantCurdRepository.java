package com.example.application_management_system.repository;

import com.example.application_management_system.Entity.Applicant;
import org.springframework.data.repository.CrudRepository;

public interface ApplicantCurdRepository  extends CrudRepository<Applicant, Long> {

}
