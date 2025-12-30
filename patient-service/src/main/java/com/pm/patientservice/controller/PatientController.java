package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidatorGroup;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping()
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping()
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidatorGroup.class})
            @RequestBody PatientRequestDTO patientRequestDTO) {

//        @Validated({Default.class, CreatePatientValidatorGroup.class})
//        Does the same thing as the @Valid tag.
//        Only difference is we're being specific about the things we want validated
//        registeredDate in PatientRequestDTO has groups = CreatePatientValidatorGroup.class

        PatientResponseDTO patientResponse = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponse); // includes other stuff such as id
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                                            @Validated({Default.class})
                                                            @RequestBody PatientRequestDTO patientRequestDTO) {
        // @Validated({Default.class}) tells Spring to validate the request using all the defaults specified in the DTO
        PatientResponseDTO patientResponse = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
