package com.example.forumpilotproject.repositories;


import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface MessageRepository extends JpaRepository<Message,Long> {
    //Message findByText(String text);
    Message findMessageById(Long id);


    //поиск по полю или идентификатору
}
