// MedecinRepository.java
package com.ma.gestionproduits.repository;

import com.ma.gestionproduits.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByNom(String nom);
}