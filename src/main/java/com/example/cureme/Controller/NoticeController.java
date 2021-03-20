package com.example.cureme.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class NoticeController {
    @GetMapping(path = "/add-notice")
    private String addNotice(){return "PatientNotice";}

    @GetMapping(path = "/notice-board")
    private String noticeBoard(){return "NoticeBoard";}
}
