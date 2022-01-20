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

        return "registration";//возвращаем страницу с регистрацией
    }

    @PostMapping("/registration")
    public String addUser(EntityUser user,Map<String,Object> model) {
//если сервис говорит, что в Бд есть уже этот пользователь - т.е. метод эдюзер возвращает фолс
    if(!userService.addUser(user)){
        model.put("message","User exists!");
        return "registration";
    }


//return "registration";
        return "redirect:/login";

    }
}
