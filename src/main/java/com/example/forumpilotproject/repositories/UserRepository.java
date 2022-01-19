package com.example.forumpilotproject.repositories;


import com.example.forumpilotproject.entities.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<EntityUser, Long> {
    EntityUser findByUsername(String username);


}
