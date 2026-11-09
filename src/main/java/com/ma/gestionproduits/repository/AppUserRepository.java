// AppUserRepository.java
package com.ma.gestionproduits.repository;

import com.ma.gestionproduits.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}