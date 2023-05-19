package com.capgemini.lifescience.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "departement")
// @Data
@Getter
@Setter
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_dep")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departement")
    private Set<Admin> admins;
}
