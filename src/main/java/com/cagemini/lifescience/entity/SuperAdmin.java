package com.cagemini.lifescience.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "super_admin")
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String sexe;
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;
    private String email;
    private String password;
    private Boolean etat;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "superAdmin" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Admin> admins;

    @OneToMany(mappedBy = "superAdmin" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Manager> managers;

    public SuperAdmin(){

    }

    public SuperAdmin(String firstName, String lastName, Date dateOfBirth, String phone, String sexe, byte[] photo, String email, String password, Boolean etat, Role role, Set<Admin> admins, Set<Manager> managers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.sexe = sexe;
        this.photo = photo;
        this.email = email;
        this.password = password;
        this.etat = etat;
        this.role = role;
        this.admins = admins;
        this.managers = managers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
