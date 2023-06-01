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
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "date_birth")
    private Date  DateBirth;
    @Column(name = "phone")
    private String phone;
    @Column(name = "sexe")
    private String sexe;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "etat")
    private Boolean etat;
    @Column(name = "photo")
    private String photo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "departement_id")
    private Departement departement;

    //@ManyToOne
    //@JoinColumn(name = "departement_id", nullable = false)
   // private Departement departement;

}
