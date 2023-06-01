package com.capgemini.lifescience.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="superadmin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;
    @Column(name= "name")
    private String name;
    @Column(name= "email")
    private String email;
    @Column(name= "phone")
    private String phone;
    @Column(name= "etat")
    private String etat;
    @Column(name= "password")
    private String password;
    @Column(name= "photo")
    private String photo;
}
