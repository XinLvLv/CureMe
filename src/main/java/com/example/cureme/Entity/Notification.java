package com.example.cureme.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer id;

    @Column(name = "title")
    private String Title;

    @Column(name = "detail")
    private String Detail;

    @Column(name = "set_date")
    private Date Date;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date setDate) {
        this.Date = setDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
