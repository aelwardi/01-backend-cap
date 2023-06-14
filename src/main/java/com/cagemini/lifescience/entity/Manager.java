package com.cagemini.lifescience.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "manager")
public class Manager {

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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    public Manager() {
    }

    public Manager(String lastName, String firstName, Date dateBirth, String phone, String sexe, String email, String password, Boolean etat, byte[] photo, Role role, Departement departement, Admin admin) {
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
        this.admin = admin;
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

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateBirth=" + dateBirth +
                ", phone='" + phone + '\'' +
                ", sexe='" + sexe + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", etat=" + etat +
                ", photo=" + Arrays.toString(photo) +
                ", role=" + role +
                ", departement=" + departement +
                ", admin=" + admin +
                '}';
    }
}
