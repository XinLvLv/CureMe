package com.example.cureme.Service;

import com.example.cureme.Entity.Patient;
import com.example.cureme.Entity.VitalSigns;
import com.example.cureme.Repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.Math;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ReadingService {
    @Autowired
    private ReadingRepository readingRepository;

    public List<VitalSigns> getPatientPulse(Integer patientId) {
        return readingRepository.getPatientPulse(patientId);
    }
}
