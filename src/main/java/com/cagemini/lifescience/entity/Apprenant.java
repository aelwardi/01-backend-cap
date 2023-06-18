package com.cagemini.lifescience.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "apprenant")
public class Apprenant {

    @Id
    // @Column(name = "admin_id",unique = true,nullable = false)
    // @GenericGenerator(name = "gen",strategy = "foreign",parameters = {@org.hibernate.annotations.Parameter(name = "property",value = "admin")})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private Date DateBirth;
    private String phone;
    private String sexe;
    private String email;
    private String password;
    private  Boolean etat;
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne()
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;



//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "super_admin_id")
//    private SuperAdmin superAdmin;



    public Apprenant(Long id, String lastName, String firstName, Date dateBirth, String phone, String sexe, String email, String password, Boolean etat, byte[] photo, Role role, Departement departement, Admin admin) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        DateBirth = dateBirth;
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

    public Apprenant(){}

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
        return DateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        DateBirth = dateBirth;
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

    //public SuperAdmin getSuperAdmin() {
//        return superAdmin;
//    }

//    public void setSuperAdmin(SuperAdmin superAdmin) {
//        this.superAdmin = superAdmin;
//    }

    @Override
    public String toString() {
        return "Apprenant{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", DateBirth=" + DateBirth +
                ", phone='" + phone + '\'' +
                ", sexe='" + sexe + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", etat=" + etat +
                ", photo='" + photo + '\'' +
                ", role=" + role +
                ", departement=" + departement +
                ", admin=" + admin +
                '}';
    }
}
