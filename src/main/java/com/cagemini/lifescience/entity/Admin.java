package com.cagemini.lifescience.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Set;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
   // @Column(name = "admin_id",unique = true,nullable = false)
    // @GenericGenerator(name = "gen",strategy = "foreign",parameters = {@org.hibernate.annotations.Parameter(name = "property",value = "admin")})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private Date dateBirth;
    private String phone;
    private String sexe;
    private String email;
    private String password;
    private  Boolean etat;
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "admin" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Manager> managers;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "super_admin_id", nullable = false)
    private SuperAdmin superAdmin;


    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Apprenant> apprenants;

    public Admin() {
    }

    public Admin(String lastName, String firstName, Date dateBirth, String phone, String sexe, String email, String password, Boolean etat, byte[] photo, Role role, Set<Manager> managers, Departement departement, SuperAdmin superAdmin, List<Apprenant> apprenants) {
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
        this.managers = managers;
        this.departement = departement;
        this.superAdmin = superAdmin;
        this.apprenants = apprenants;
    }

    public long getId() {
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

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
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

    public List<Apprenant> getApprenants() {
        return apprenants;
    }

    public void setApprenants(List<Apprenant> apprenants) {
        this.apprenants = apprenants;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public SuperAdmin getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(SuperAdmin superAdmin) {
        this.superAdmin = superAdmin;
    }

    public Set<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Set<Manager> managers) {
        this.managers = managers;
    }
}
