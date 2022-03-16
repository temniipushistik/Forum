package com.example.forumpilotproject.controllers;

import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController  {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {

        return "registration";//
    }

    @PostMapping("/registration")
    public String addUser(EntityUser user,Map<String,Object> model) {
//if service response that BD already has the user the current method "addUser" return false
    if(!userService.addUser(user)){
        model.put("message","User exists!");
        return "registration";
    }

        return "redirect:/login";

    }
}
