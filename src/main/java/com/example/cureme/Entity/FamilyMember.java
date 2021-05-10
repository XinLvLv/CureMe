package com.example.cureme.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "family_member")
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_member_id")
    private Integer familyMemberId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(targetEntity = Patient.class, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_patient_family_member",
    joinColumns = {@JoinColumn(name = "sys_family_member_id", referencedColumnName = "family_member_id")},
    inverseJoinColumns = {@JoinColumn(name = "sys_patient_id", referencedColumnName = "patient_id")})
    private Set<Patient> patients = new HashSet<>();


    public Integer getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Integer familyMemberId) {
        this.familyMemberId = familyMemberId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(){
        String pwd = "";
        for (int i = 1; i<9; i++){
            double random = Math.random();
            double random_tri =random*3;
            if (random_tri >= 0 && random_tri < 1) {
                pwd += (char) (random * ('9' - '0') + '0');
            } else if (random_tri >= 1 && random_tri < 2) {
                pwd += (char) (random * ('Z' - 'A') + 'A');
            } else {
                pwd += (char) (random * ('z' - 'a') + 'a');
            }
        }
        password = pwd;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
