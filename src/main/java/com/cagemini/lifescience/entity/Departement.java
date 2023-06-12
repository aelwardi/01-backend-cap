package com.cagemini.lifescience.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name_dep")
    private String name;
    @OneToMany(mappedBy = "departement" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Admin> admins;

    @OneToMany(mappedBy = "departement" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Manager> managers;

    public Departement(){}

    public Departement(String name, Set<Admin> admins, Set<Manager> managers) {
        this.name = name;
        this.admins = admins;
        this.managers = managers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }

    public Set<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Set<Manager> managers) {
        this.managers = managers;
    }

}
