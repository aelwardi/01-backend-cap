package com.cagemini.lifescience.model;

import com.cagemini.lifescience.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

public class ManagerInfos {
    private Long id;
    private String lastName;
    private String firstName;
    private Date dateBirth;
    private String phone;
    private String sexe;
    private String email;
    private String password;
    private  Boolean etat;
    private byte[] photo;
    @Enumerated(EnumType.STRING)
    private Role role;
    private DepartementInfo departement;

    public ManagerInfos() {
    }

    public ManagerInfos(Long id, String lastName, String firstName, Date dateBirth, String phone, String sexe, String email, String password, Boolean etat, byte[] photo, Role role, DepartementInfo departement) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.sexe = sexe;
        this.email = email;
        this.password = password;
        this.etat = etat;
        this.photo = photo;
        this.role = role;
        this.departement = departement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public DepartementInfo getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementInfo departement) {
        this.departement = departement;
    }
}
