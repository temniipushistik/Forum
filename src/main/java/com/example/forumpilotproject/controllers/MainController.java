package com.example.forumpilotproject.controllers;

import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.entities.Message;
import com.example.forumpilotproject.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//Checking connection between view and server via REST

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "greeting";

        //  public ResponseEntity<HttpStatus> check() {
        //  return new ResponseEntity<>(HttpStatus.OK);


    }
    //получаем все сообщения и возвращаем их в мейн
    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal EntityUser user,
                      @RequestParam String text, Map<String, Object> model) {
        //сохраняем полученные сообщения
        Message message = new Message(text,user);
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();

        //положили в репозиторий
        model.put("messages", messages);
        //отдали пользователю
        return "main";
    }
    @GetMapping("/textOfTopic/{idOfTopic}")
    public String textIdOfTopic(@PathVariable(value = "idOfTopic") long idOfTopic,Model model){
     Optional<Message> messages = messageRepository.findById(idOfTopic);
     model.addAttribute("topic",messages.get().getText());
     return "textOfTopic";
    }

  //  @PostMapping("/textOfTopic/{idOfTopic}")
  //  public String t












}