package edu.keyin.stephencrocker.controller;

import edu.keyin.stephencrocker.service.BSTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class BSTController {

    @Autowired
    private BSTService bstService;

    @GetMapping("/enter-numbers")
    public String showInputPage() {
        return "input-page";
    }


}