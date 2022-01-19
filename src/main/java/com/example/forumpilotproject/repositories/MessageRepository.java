package com.example.forumpilotproject.repositories;


import com.example.forumpilotproject.entities.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface MessageRepository extends CrudRepository<Message,Long> {
    //поиск по полю или идентификатору
}
