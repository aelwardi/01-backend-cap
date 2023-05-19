package com.capgemini.lifescience.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "admin")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_birth")
    private Date dateBirth;
    @Column(name = "phone")
    private String phone;
    @Column(name = "sexe")
    private String sexe;
    @Column(name = "photo")
    private String photo;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String pw;
    @Column(name = "etat")
    private Boolean etat;
    @ManyToOne
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;
}
