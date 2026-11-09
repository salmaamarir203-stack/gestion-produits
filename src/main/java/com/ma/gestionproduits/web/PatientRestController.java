package com.ma.gestionproduits.web;

import com.ma.gestionproduits.entities.Patient;
import com.ma.gestionproduits.repository.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {
    private final PatientRepository patientRepository;

    public PatientRestController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    public List<Patient> patientList() {
        return patientRepository.findAll();
    }
}