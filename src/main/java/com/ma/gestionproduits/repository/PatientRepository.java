// PatientRepository.java
package com.ma.gestionproduits.repository;

import com.ma.gestionproduits.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByNom(String nom);
}