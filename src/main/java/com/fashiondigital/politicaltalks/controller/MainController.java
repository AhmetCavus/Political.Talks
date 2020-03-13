package com.fashiondigital.politicaltalks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

	@GetMapping
    public ResponseEntity<Object> indexAction() 
    {
		 return new ResponseEntity<Object>("/evaluation?url1={Path to the csv file}&url2={Optional Path to the csv file}", HttpStatus.OK);
    }
}