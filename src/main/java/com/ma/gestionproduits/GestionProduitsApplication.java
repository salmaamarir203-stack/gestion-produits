package com.ma.gestionproduits;

import com.ma.gestionproduits.entities.Product;
import com.ma.gestionproduits.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GestionProduitsApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionProduitsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Ajouter
        productRepository.save(new Product(null, "Computer", 4300, 3));
        productRepository.save(new Product(null, "Printer", 1200, 4));
        productRepository.save(new Product(null, "Smartphone", 3200, 12));

        // 2. Consulter tous les produits
        List<Product> products = productRepository.findAll();
        products.forEach(p -> System.out.println(p.toString()));

        // 3. Consulter un produit
        Product product = productRepository.findById(1L).get();
        System.out.println("**********");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQuantity());
        System.out.println("**********");

        // 4. Chercher des produits
        System.out.println("--- Nom contient 'C' ---");
        productRepository.findByNameContains("C").forEach(System.out::println);
        System.out.println("--- Avec @Query ---");
        productRepository.search("%C%").forEach(System.out::println);
        System.out.println("--- Prix > 3000 ---");
        productRepository.findByPriceGreaterThan(3000).forEach(System.out::println);
        productRepository.searchByPrice(3000).forEach(System.out::println);

        // 5. Mettre à jour
        product.setPrice(4000);
        productRepository.save(product);   // id existant → UPDATE

        // 6. Supprimer
        productRepository.deleteById(2L);
    }
}