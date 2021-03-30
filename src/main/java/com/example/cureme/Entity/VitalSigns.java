package com.example.cureme.Entity;

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
    @Column(name = "temperature")
    private Float temperature;

    @Column(name = "pulse")
    private Integer pulse;

    @Column(name = "breathing_rate")
    private Integer breathingRate;

    //vital signs of apnea
//    @Column(name = "pulse")
//    private Integer pulse;

    @Column(name = "spo2")
    private Float spo2;

    //vital signs of hypotension
    @Column(name = "systolic_BP")
    private Integer systolicBP;

    @Column(name = "decreasing_MAP")
    private Integer decreasingMAP;

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

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Integer getBreathingRate() {
        return breathingRate;
    }

    public void setBreathingRate(Integer breathingRate) {
        this.breathingRate = breathingRate;
    }

    public Float getSpo2() {
        return spo2;
    }

    public void setSpo2(Float spo2) {
        this.spo2 = spo2;
    }

    public Integer getSystolicBP() {
        return systolicBP;
    }

    public void setSystolicBP(Integer systolicBP) {
        this.systolicBP = systolicBP;
    }

    public Integer getDecreasingMAP() {
        return decreasingMAP;
    }

    public void setDecreasingMAP(Integer decreasingMAP) {
        this.decreasingMAP = decreasingMAP;
    }
}
