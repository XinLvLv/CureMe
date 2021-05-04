package com.example.cureme.Entity;

import javax.persistence.*;

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

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
