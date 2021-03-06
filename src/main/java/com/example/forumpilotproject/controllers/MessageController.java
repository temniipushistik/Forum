package com.example.forumpilotproject.controllers;

import com.example.forumpilotproject.entities.EntityComments;
import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.entities.Message;
import com.example.forumpilotproject.repositories.CommentsRepository;
import com.example.forumpilotproject.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/textOfTopic/{idOfTopic}")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    CommentsRepository commentsRepository;

    @GetMapping
    public String textIdOfTopic(
            @AuthenticationPrincipal EntityUser user,
            @PathVariable(value = "idOfTopic") long idOfTopic, Model model) {
        Message message = messageRepository.findMessageById(idOfTopic);
        Iterable<EntityComments> comments = message.getEntityComments();

        model.addAttribute("topic", message.getText());
        model.addAttribute("authorId", message.getAuthor().getId());
        model.addAttribute("author", message.getAuthorName());
        model.addAttribute("currentUserId", user.getId());

        model.addAttribute("user", user);
      //  model.addAttribute("dataOfComment",dataOfComment);
        model.addAttribute("data", message.getDate());

        model.addAttribute("comments", comments);
       // model.addAttribute("dataOfComment",com)
        if (message.getDescription() != null) {
            model.addAttribute("description", message.getDescription());

        }

        return "textOfTopic";
    }

    @PostMapping
    public String addArticle(@AuthenticationPrincipal EntityUser user,
                             @PathVariable(value = "idOfTopic") long idOfTopic,
                             @RequestParam String textOfArticle, Model model) {
        Message message = messageRepository.findMessageById(idOfTopic);
        message.setDescription(textOfArticle);
        String data = (new Date()).toString();
        message.setDate(data);
        messageRepository.save(message);
        model.addAttribute("topic", message.getText());
        model.addAttribute("authorId", message.getAuthor().getId());
        //model.addAttribute("isAdmin",user.isAdmin());
        model.addAttribute("user", user);
        model.addAttribute("description", message.getDescription());
        model.addAttribute("currentUserId", user.getId());
        model.addAttribute("author", message.getAuthorName());
        model.addAttribute("data", message.getDate());


        return "textOfTopic";
    }

    @GetMapping("/edit")
    public String getArticleEdit(
            @PathVariable(value = "idOfTopic") long idOfTopic, Model model) {
        Message message = messageRepository.findMessageById(idOfTopic);
        model.addAttribute("topic", message.getText());

        if (message.getDescription() != null) {
            model.addAttribute("description", message.getDescription());
        }

        return "textEdit";
    }

    @PostMapping("/edit")
    public String postArticleEdit(
            @AuthenticationPrincipal EntityUser user,
            @PathVariable(value = "idOfTopic") long idOfTopic,
            @RequestParam String textOfArticle, @RequestParam String topic, Model model) {

        Message message = messageRepository.findMessageById(idOfTopic);
        message.setText(topic);
        message.setDescription(textOfArticle);
        messageRepository.save(message);
        model.addAttribute("authorId", message.getAuthor().getId());//?????????? ??????????
        model.addAttribute("userId", user.getId());//?????????????? ????????

        return "redirect:/textOfTopic/{idOfTopic}";
    }


    @PostMapping("/remove")
    public String ArticleDelete(@AuthenticationPrincipal EntityUser user, @PathVariable(value = "idOfTopic") long idOfTopic,
                                Model model) {
        //  Message message = new Message(user);
        messageRepository.deleteById(idOfTopic);
        //  model.addAttribute("authorId", message.getAuthor().getId());

        return "redirect:/main";
    }

}
