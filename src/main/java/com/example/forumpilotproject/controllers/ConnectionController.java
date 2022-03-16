package com.example.forumpilotproject.controllers;
import org.springframework.web.bind.annotation.GetMapping;


//Checking connection between view and server

    public class ConnectionController {
      	@GetMapping("/greeting")
        public String greeting() {

            return "greeting";
        }

    }







