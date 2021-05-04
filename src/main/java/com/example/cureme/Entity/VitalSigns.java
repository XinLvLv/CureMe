package com.example.cureme.Entity;

import org.springframework.data.relational.core.sql.In;

import javax.lang.model.element.Name;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vital_signs")
public class VitalSigns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vital_signs_id")
    private Integer vitalSignId;

    @Column(name = "measuring_time")
    private Date datetime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    //vital signs of sepsis
    @Column(name = "breathing_rate")
    private Integer breathingRate;

    @Column(name = "GCS_score")
    private Integer GCS;

    @Column(name = "systolic_BP")
    private Integer systolicBP;

    //vital signs of apnea
    @Column(name = "pulse")
    private Integer pulse;

    @Column(name = "spo2")
    private Integer spo2;

    //vital signs of hypotension
//    @Column(name = "systolic_BP")
//    private Integer systolicBP;

    @Column(name = "diastolic_BP")
    private Integer diastolicBP;

    public Integer getVitalSignId() {
        return vitalSignId;
    }

    public void setVitalSignId(Integer vitalSignId) {
        this.vitalSignId = vitalSignId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getBreathingRate() {
        return breathingRate;
    }

    public void setBreathingRate(Integer breathingRate) {
        this.breathingRate = breathingRate;
    }

    public Integer getGCS() {
        return GCS;
    }

    public void setGCS(Integer GCS) {
        this.GCS = GCS;
    }

    public Integer getSystolicBP() {
        return systolicBP;
    }

    public void setSystolicBP(Integer systolicBP) {
        this.systolicBP = systolicBP;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Integer getDiastolicBP() {
        return diastolicBP;
    }

    public void setDiastolicBP(Integer diastolicBP) {
        this.diastolicBP = diastolicBP;
    }
}
