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
    private Double temperature;

    @Column(name = "pulse_num")
    private Integer pulseNum;

    @Column(name = "breathing_rate")
    private Integer breathingRate;

    //vital signs of apnea
//    @Column(name = "pulseNum")
//    private Integer pulseNum;

    @Column(name = "spo2")
    private Double spo2;

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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getPulse() {
        return pulseNum;
    }

    public void setPulse(Integer pulse) {
        this.pulseNum = pulse;
    }

    public Integer getBreathingRate() {
        return breathingRate;
    }

    public void setBreathingRate(Integer breathingRate) {
        this.breathingRate = breathingRate;
    }

    public Double getSpo2() {
        return spo2;
    }

    public void setSpo2(Double spo2) {
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
