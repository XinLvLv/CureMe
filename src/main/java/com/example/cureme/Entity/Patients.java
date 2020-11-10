package com.example.cureme.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Patients")
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "first_name")
    private String FirstName;
    @Column(name = "last_name")
    private String LastName;
    @Column(name = "Address")
    private String Address;
    @Column(name = "phone_number")
    private String PhoneNumber;
    @Column(name = "Mail")
    private String Mail;
    @Column(name = "Disease")
    private String Disease;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Gender")
    private String Gender;
    @Column(name = "DOB")
    private Date DOB;
    @Column(name = "Age")
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(){
        String password = "";
        for (int i = 1; i<9; i++){
            double random = Math.random();
            double random_tri =random*3;
            if (random_tri >= 0 && random_tri < 1) {
                password += (char) (random * ('9' - '0') + '0');
            } else if (random_tri >= 1 && random_tri < 2) {
                password += (char) (random * ('Z' - 'A') + 'A');
            } else {
                password += (char) (random * ('z' - 'a') + 'a');
            }
        }
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date dob) {
        DOB = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
