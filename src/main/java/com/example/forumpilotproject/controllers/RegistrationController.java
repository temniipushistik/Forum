package com.example.forumpilotproject.controllers;

import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.entities.Role;
import com.example.forumpilotproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController  {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {

        return "registration";//возвращаем страницу с регистрацией
    }

    @PostMapping("/registration")
    public String addUser(EntityUser user, Map<String, Object> model) {

        EntityUser userFromDB = userRepository.findByUsername(user.getUsername());
        //если имя есть мы об этом сообщаем и возвращаем тест на эту же страницу
        if (userFromDB != null) {
            model.put("message", "User exists");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
//return "registration";
        return "redirect:/login";

    }
}
