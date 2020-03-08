package com.fashiondigital.politicaltalks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ExceptionController {
    
	@GetMapping
    public String printError() {
        return "Error";
    }
}