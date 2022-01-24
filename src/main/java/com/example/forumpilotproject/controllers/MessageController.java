package com.example.forumpilotproject.controllers;

import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.entities.Message;
import com.example.forumpilotproject.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/textOfTopic/{idOfTopic}")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    @GetMapping
    public String textIdOfTopic(@PathVariable(value = "idOfTopic") long idOfTopic, Model model) {
        Message message = messageRepository.findMessageById(idOfTopic);
        model.addAttribute("topic", message.getText());

        return "textOfTopic";
    }

    @PostMapping
    public String addArticle(@PathVariable(value = "idOfTopic") long idOfTopic,
                             @RequestParam String textOfArticle, Model model) {
        Message message = messageRepository.findMessageById(idOfTopic);
        message.setDescription(textOfArticle);
        model.addAttribute("topic", message.getText());
        model.addAttribute("description", message.getDescription());


        return "textOfTopic";
    }

}
