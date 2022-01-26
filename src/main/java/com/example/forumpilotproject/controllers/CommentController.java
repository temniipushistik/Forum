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

import java.util.Map;

@Controller
@RequestMapping("/textOfTopic/{idOfTopic}/comments")
public class CommentController {
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    MessageRepository messageRepository;

    @GetMapping
    public String addComment(Model model, @PathVariable(value = "idOfTopic") long idOfTopic) {
        Message message = messageRepository.findMessageById(idOfTopic);
        Iterable<EntityComments> comments = commentsRepository.findEntityCommentsByCommentedPost(message);
                model.addAttribute("comments", comments);
        return "comments";
    }


    @PostMapping
    public String addComment(@AuthenticationPrincipal EntityUser user,
                             @RequestParam String textOfComment, Model model, @PathVariable Long idOfTopic) {
        Message message = messageRepository.findMessageById(idOfTopic);
        EntityComments comments = new EntityComments(textOfComment, user, message);
        commentsRepository.save(comments);
        Iterable<EntityComments> commentsList = commentsRepository.findEntityCommentsByCommentedPost(message);
        model.addAttribute("comments", commentsList);
        model.addAttribute("author", comments.getAuthorOfComment().getUsername());
        return "comments";

    }
}
