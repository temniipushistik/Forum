package com.example.forumpilotproject.repositories;

import com.example.forumpilotproject.entities.EntityComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<EntityComments,Long> {
    EntityComments findCommentById(Long idOfComment);
    //Iterable<EntityComments>  findEntityCommentsById(Message message);


}
