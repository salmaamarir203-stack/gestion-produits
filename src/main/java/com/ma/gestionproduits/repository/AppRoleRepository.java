// AppRoleRepository.java
package com.ma.gestionproduits.repository;

import com.ma.gestionproduits.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}