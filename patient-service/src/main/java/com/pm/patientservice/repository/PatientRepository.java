package com.pm.patientservice.repository;

import com.pm.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);

    /**
     * searches db for any patients that have an email address
     * will ignore the patient we're trying to update and its search results
     * checks if there's another patient in the db with the same email as the one trying to be updated
     * but with a different id
     *
     * @param email - patient email
     * @param id    - patient id
     * @return boolean
     */
    boolean existsByEmailAndIdNot(String email, UUID id);
}
