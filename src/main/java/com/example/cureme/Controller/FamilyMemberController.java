package com.example.cureme.Controller;

import com.example.cureme.Entity.Doctor;
import com.example.cureme.Entity.FamilyMember;
import com.example.cureme.Entity.Patient;
import com.example.cureme.Service.FamilyMemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class FamilyMemberController {
    @Autowired
    private FamilyMemberService familyMemberService;

    @GetMapping(path="/family-member-login")
    private String familyMemberLogin() { return "FamilyMemberLogin"; }

    @PostMapping(path = "/family-member-login-submit-form")
    public String logIn(@RequestParam String userName, String password){
        List<FamilyMember> familyMembers = familyMemberService.selectFamilyMemberByUserName(userName);
        //user doesn't exist
        if(familyMembers.size()==0){
            return "redirect:/family-member-login";
        }
        else {
            //correct password
            if(familyMembers.get(0).getPassword().equals(password)){
                HttpSession session = getRequest().getSession();
                session.setAttribute("currentUserId", familyMembers.get(0).getFamilyMemberId());
                return "redirect:/family-member-home";
            }
            //invalid password
            else {
                return "redirect:/family-member-login";
            }
        }
    }

    @GetMapping(path = "/family-member-home")
    private String familyMemberHome(Model model) {
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<Patient> patients= familyMemberService.viewPatients(currentUserId);
        model.addAttribute("patients", patients);
        return "FamilyMemberHome";}


    @GetMapping(path="/add-family-member")
    private String doctorLogin() { return "AddFamilyMember"; }

    @PostMapping(path = "/add-family-member-submit-form")
    public String addFamilyMemberSubmitForm(@RequestParam String firstName, String lastName,String email){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        familyMemberService.add(currentUserId, firstName, lastName, email);
        return "redirect:/my-family-member";
    }

    @GetMapping(path = "/my-family-member")
    public String myFamilyMember(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        Set<FamilyMember> familyMembersSet = familyMemberService.myFamilyMember(currentUserId);
        List<FamilyMember> familyMembersList = new ArrayList<>(familyMembersSet);
        model.addAttribute("familyMembers", familyMembersList);
        return "FamilyMemberList";
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
