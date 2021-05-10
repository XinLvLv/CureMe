package com.example.cureme.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "disease")
    private String disease;

    @Column(name = "status")
    private String status;

    @Column(name = "schedule")
    private Integer schedule;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @JsonIgnoreProperties(value = { "patients" })
    @ManyToMany(targetEntity = FamilyMember.class, cascade = CascadeType.ALL)
    @JoinTable(name = "sys_patient_family_member",
            joinColumns = {@JoinColumn(name = "sys_patient_id", referencedColumnName = "patient_id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_family_member_id", referencedColumnName = "family_member_id")})
    private Set<FamilyMember> familyMembers = new HashSet<>();

    //Getters and Setters
    public Integer getPatientId() {
        return patientId;
    }

    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    //random password
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Set<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(Set<FamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }
}
