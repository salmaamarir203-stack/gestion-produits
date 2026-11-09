package com.ma.gestionproduits.repository;

import com.ma.gestionproduits.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Méthodes dérivées du nom (convention findBy...)
    List<Product> findByNameContains(String keyword);
    List<Product> findByPriceGreaterThan(double price);

    // Même chose avec @Query (JPQL)
    @Query("select p from Product p where p.name like :x")
    List<Product> search(@Param("x") String keyword);

    @Query("select p from Product p where p.price > :x")
    List<Product> searchByPrice(@Param("x") double price);
}