package com.example.forumpilotproject.controllers;

import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.entities.Role;
import com.example.forumpilotproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "userList";
    }
    @GetMapping("{user}")
        public String userEditForm(@PathVariable EntityUser user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
@PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") EntityUser user){//request param - запрос с сервера?
        user.setUsername(username);//что делает строка?
    //получаем в виде коллекции стрингов роли
    Set<String> roles = Arrays.stream(Role.values())
            .map(Role::name).
            collect(Collectors.toSet());
    user.getRoles().clear();
    for(String key:form.keySet()){
        if(roles.contains(key)){
            user.getRoles().add(Role.valueOf(key));
        }
    }
        userRepository.save(user);


        return "redirect:/user";
}


}
