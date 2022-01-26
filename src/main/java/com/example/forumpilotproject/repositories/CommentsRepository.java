package com.example.forumpilotproject.repositories;

import com.example.forumpilotproject.entities.EntityComments;
import com.example.forumpilotproject.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<EntityComments,Long> {
    Iterable<EntityComments>  findEntityCommentsByCommentedPost(Message message);

}
