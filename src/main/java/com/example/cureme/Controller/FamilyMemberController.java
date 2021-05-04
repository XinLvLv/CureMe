package com.example.cureme.Controller;

import com.example.cureme.Entity.Doctor;
import com.example.cureme.Entity.FamilyMember;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class FamilyMemberController {
    @Autowired
    private FamilyMemberService familyMemberService;

    @GetMapping(path="/add-family-member")
    private String doctorLogin() { return "AddFamilyMember"; }

    @PostMapping(path = "/add-family-member-submit-form")
    public String addFamilyMemberSubmitForm(@RequestParam String firstName, String lastName,String email, String relationship){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        familyMemberService.add(currentUserId, firstName, lastName, email, relationship);
        return "redirect:/my-family-member";
    }

    @GetMapping(path = "/my-family-member")
    public String myFamilyMember(Model model){
        HttpSession session = getRequest().getSession();
        Integer currentUserId = (Integer) session.getAttribute("currentUserId");
        List<FamilyMember> familyMembers = familyMemberService.selectByPatientId(currentUserId);
        model.addAttribute("familyMembers", familyMembers);
        return "FamilyMemberList";
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
