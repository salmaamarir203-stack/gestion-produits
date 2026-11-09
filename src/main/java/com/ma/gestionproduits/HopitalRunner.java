package com.ma.gestionproduits;

import com.ma.gestionproduits.entities.*;
import com.ma.gestionproduits.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class HopitalRunner implements CommandLineRunner {

    @Autowired private PatientRepository patientRepository;
    @Autowired private MedecinRepository medecinRepository;
    @Autowired private RendezVousRepository rendezVousRepository;
    @Autowired private ConsultationRepository consultationRepository;
    @Autowired private AppUserRepository appUserRepository;
    @Autowired private AppRoleRepository appRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Patients
        patientRepository.save(Patient.builder().nom("Mohamed").dateNaissance(new Date()).malade(false).build());
        patientRepository.save(Patient.builder().nom("Hassan").dateNaissance(new Date()).malade(true).build());
        patientRepository.save(Patient.builder().nom("Najat").dateNaissance(new Date()).malade(true).build());

        // Médecins
        medecinRepository.save(Medecin.builder().nom("Aymane").email("aymane@gmail.com").specialite("Cardiologue").build());
        medecinRepository.save(Medecin.builder().nom("Hanane").email("hanane@gmail.com").specialite("Dentiste").build());

        // Rendez-vous
        Patient patient = patientRepository.findByNom("Mohamed");
        Medecin medecin = medecinRepository.findByNom("Aymane");

        RendezVous rdv = RendezVous.builder()
                .date(new Date())
                .status(StatusRDV.PENDING)
                .patient(patient)
                .medecin(medecin)
                .build();
        rendezVousRepository.save(rdv);

        // Consultation
        Consultation consultation = Consultation.builder()
                .dateConsultation(new Date())
                .rapport("Rapport de la consultation...")
                .rendezVous(rdv)
                .build();
        consultationRepository.save(consultation);

        // Roles et users
        appRoleRepository.save(AppRole.builder().roleName("ADMIN").build());
        appRoleRepository.save(AppRole.builder().roleName("USER").build());

        AppUser user = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username("user1")
                .password("1234")
                .active(true)
                .build();
        user.getRoles().add(appRoleRepository.findByRoleName("USER"));
        appUserRepository.save(user);

        AppUser admin = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username("admin")
                .password("1234")
                .active(true)
                .build();
        admin.getRoles().add(appRoleRepository.findByRoleName("USER"));
        admin.getRoles().add(appRoleRepository.findByRoleName("ADMIN"));
        appUserRepository.save(admin);

        // Affichage
        System.out.println("===== Patients =====");
        patientRepository.findAll().forEach(p ->
                System.out.println(p.getId() + " " + p.getNom() + " malade=" + p.isMalade()));

        System.out.println("===== Rendez-vous =====");
        rendezVousRepository.findAll().forEach(r ->
                System.out.println(r.getId() + " " + r.getStatus() + " patient=" + r.getPatient().getNom()
                        + " médecin=" + r.getMedecin().getNom()));

        System.out.println("===== Users =====");
        appUserRepository.findAll().forEach(u -> {
            System.out.print(u.getUsername() + " -> ");
            u.getRoles().forEach(r -> System.out.print(r.getRoleName() + " "));
            System.out.println();
        });
    }
}