package com.example.cureme.Controller;

import com.example.cureme.Entity.Doctor;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.DoctorService;
import com.example.cureme.Service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        Doctor currentUser = (Doctor) session.getAttribute("currentUser");
        List<Patient> patients =  patientsService.currentUserPatients(currentUser.getDoctorId());
        model.addAttribute("currentUser", currentUser);
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

    @PostMapping(path = "/login")
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
                session.setAttribute("currentUser", doctors.get(0));
                return "redirect:/home";
            }
            //invalid password
            else {
                return "redirect:/";
            }
        }
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
