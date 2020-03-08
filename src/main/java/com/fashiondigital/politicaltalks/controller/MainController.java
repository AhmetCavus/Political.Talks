package com.fashiondigital.politicaltalks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class MainController {
    
	@GetMapping
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("main");
        return modelAndView;
    }
}