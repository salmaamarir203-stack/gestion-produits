// ConsultationRepository.java
package com.ma.gestionproduits.repository;

import com.ma.gestionproduits.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}