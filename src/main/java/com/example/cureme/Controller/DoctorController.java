package com.example.cureme.Controller;

import com.example.cureme.Entity.Doctor;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.DoctorService;
import com.example.cureme.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientsService patientsService;

    @GetMapping(path = "/account")
    private String account(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        Doctor doctor = doctorService.currentDoctor(currentUserId).get(0);
        List<Patient> patients =  patientsService.currentUserPatients(currentUserId);
        model.addAttribute("currentUser", doctor);
        model.addAttribute("currentUserPatients", patients);
        return "Account";
    }

    @GetMapping(path = "/sign-up")
    private String signUp(){return "SignUp";}

    @PostMapping(path = "/sign-up-submit-form")
    public String signUpSubmitForm(@RequestParam String firstName, String lastName, String briefIntroduction, String specialization, String phoneNumber,
                                   String email, String password, Date startOfCareer, Date dateOfBirth ){
        List<Doctor> doctors = doctorService.currentDoctor(email);
        //user already exists
        if (doctors.size()!=0){
            return "redirect:/sign-up";
        }
        //successfully sign up
        else {
            doctorService.add(firstName, lastName, briefIntroduction, specialization, phoneNumber,
                    email, password, startOfCareer, dateOfBirth);
            return "redirect:";
        }
    }

    @GetMapping(path="/doctor-login")
    private String doctorLogin() { return "DoctorLogin"; }

    @GetMapping(path = "/doctor-home")
    private String doctorHome(Model model) {
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        Doctor doctor = doctorService.currentDoctor(currentUserId).get(0);
        model.addAttribute("currentUser", doctor);
        return "DoctorHome"; }

    @PostMapping(path = "/doctor-login-submit-form")
    public String logIn(@RequestParam String userName, String password){
        List<Doctor> doctors = doctorService.currentDoctor(userName);
        //user doesn't exist
        if(doctors.size()==0){
            return "redirect:/sign-up";
        }
        else {
            //correct password
            if(doctors.get(0).getPassword().equals(password)){
                HttpSession session = getRequest().getSession();
                session.setAttribute("currentUserId", doctors.get(0).getDoctorId());
                return "redirect:/doctor-home";
            }
            //invalid password
            else {
                return "redirect:/";
            }
        }
    }

    @PostMapping(path = "/doctor/edit-doctor-information/{doctorId}")
    private String editDoctorInformation(@PathVariable Integer doctorId, @RequestParam String firstName, String lastName,
                                         String briefIntroduction, String specialization, String phoneNumber,
                                         String email, Date startOfCareer, Date dateOfBirth ){
        doctorService.editDoctorInformation(doctorId, firstName, lastName, briefIntroduction, specialization, phoneNumber,
                email, startOfCareer, dateOfBirth);
        return "redirect:/account";
    }

    @PostMapping(path = "/doctor/change-password/{doctorId}")
    private String changePassword(@PathVariable Integer doctorId, @RequestParam String oldPassword, String newPassword,
                                  String confirmPassword){

        List<Doctor> doctors = doctorService.currentDoctor(doctorId);
        //change successfully
        if (doctors.get(0).getPassword().equals(oldPassword) && newPassword.equals(confirmPassword)){
            doctorService.changePassword(doctorId, newPassword);
            return "redirect:/home";
        }
        //invalid old password
        //the two new password are not the same
        else return "redirect:/account";
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
