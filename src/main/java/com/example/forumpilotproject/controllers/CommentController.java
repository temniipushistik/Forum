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

import java.util.List;

@Controller
@RequestMapping("/textOfTopic/{idOfTopic}/comments")
public class CommentController {
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    MessageRepository messageRepository;

   /* @GetMapping
    public String addComment(Model model, @PathVariable(value = "idOfTopic") long idOfTopic) {
        Message message = messageRepository.findMessageById(idOfTopic);
        Iterable<EntityComments> comments = commentsRepository.findEntityCommentsByCommentedPost(message);
                model.addAttribute("comments", comments);
        return "comments";
   }

    */


    @PostMapping
    public String addComment(@AuthenticationPrincipal EntityUser user,
                             @RequestParam String textOfComment, Model model, @PathVariable Long idOfTopic) {

        Message message = messageRepository.findMessageById(idOfTopic);
        EntityComments comments = new EntityComments(textOfComment, user);
        // EntityComments comments = message.getEntityComments().add(new EntityComments(textOfComment,user));
        EntityComments newComments = commentsRepository.save(comments);//выгружаю из БД и получает, что сохранилось
        message.getEntityComments().add(newComments);//получаю список комментарии и добавляю сохраненный коммент
        messageRepository.save(message);
       Iterable<EntityComments> commentsList = message.getEntityComments();
      model.addAttribute("comments", commentsList);
        model.addAttribute("topic", message.getText());
       model.addAttribute("currentUserId", user.getId());
       model.addAttribute("authorId", message.getAuthor().getId());
       model.addAttribute("author", comments.getAuthorOfComment().getUsername());

        return "redirect:/textOfTopic/{idOfTopic}";

    }
}
