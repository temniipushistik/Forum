package com.example.forumpilotproject.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Checking connection between view and server via REST

    public class ConnectionController {
      	@GetMapping("/greeting")
        public String greeting() {

            return "greeting";
        }

    }

      //  public ResponseEntity<HttpStatus> check() {
          //  return new ResponseEntity<>(HttpStatus.OK);







