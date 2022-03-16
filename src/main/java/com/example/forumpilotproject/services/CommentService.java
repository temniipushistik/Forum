package com.example.forumpilotproject.services;

import com.example.forumpilotproject.entities.EntityComments;
import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.entities.Message;
import com.example.forumpilotproject.repositories.CommentsRepository;
import com.example.forumpilotproject.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    CommentsRepository commentsRepository;
    //prepare  method for code review
    public void addComments(Long idOfTopic, String textOfComment, EntityUser user) {
        Message message = messageRepository.findMessageById(idOfTopic);
        String dataOfComment = (new Date()).toString();
        EntityComments comments = new EntityComments(textOfComment, user, dataOfComment);
        EntityComments newComments = commentsRepository.save(comments);
        message.getEntityComments().add(newComments);
        messageRepository.save(message);
        Iterable<EntityComments> commentsList = message.getEntityComments();
    }

}
