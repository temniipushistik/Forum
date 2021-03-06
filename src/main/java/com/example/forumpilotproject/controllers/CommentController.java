package com.example.forumpilotproject.controllers;

import com.example.forumpilotproject.entities.EntityComments;
import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.entities.Message;
import com.example.forumpilotproject.repositories.CommentsRepository;
import com.example.forumpilotproject.repositories.MessageRepository;
import com.example.forumpilotproject.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/textOfTopic/{idOfTopic}")
public class CommentController {
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    CommentService commentService;

    @PostMapping("/comments")
    public String addComment(@AuthenticationPrincipal EntityUser user,
                             @RequestParam String textOfComment, Model model, @PathVariable Long idOfTopic) {

        Message message = messageRepository.findMessageById(idOfTopic);
        String dataOfComment = (new Date()).toString();
        EntityComments comments = new EntityComments(textOfComment, user, dataOfComment);
        EntityComments newComments = commentsRepository.save(comments);//save to BD and upload from BD
        message.getEntityComments().add(newComments);//receive list of comments and put saved comments
        Iterable<EntityComments> commentsList = message.getEntityComments();
        model.addAttribute("comments", commentsList);
        model.addAttribute("dataOfComment", comments.getDataOfComment());
        model.addAttribute("topic", message.getText());
        model.addAttribute("currentUserId", user.getId());
        model.addAttribute("authorId", message.getAuthor().getId());
        model.addAttribute("author", comments.getAuthorOfComment().getUsername());

        return "redirect:/textOfTopic/{idOfTopic}";

    }


    @PostMapping("/editComment/{comment.id}")
    public String CommentEdit(@AuthenticationPrincipal EntityUser user, @RequestParam String textOfEditedComment, Model model,
                              @PathVariable(value = "comment.id") Long idOfComment
    ) {
        EntityComments comment = commentsRepository.findCommentById(idOfComment);
        comment.setTextOfComment(textOfEditedComment);
        commentsRepository.save(comment);


        return "redirect:/textOfTopic/{idOfTopic}";
    }

    @PostMapping("/removeComment/{comment.id}")
    public String CommentDelete(
            @PathVariable(value = "comment.id") long idOfComment
    ) {

        commentsRepository.deleteById(idOfComment);


        return "redirect:/textOfTopic/{idOfTopic}";
    }


}
