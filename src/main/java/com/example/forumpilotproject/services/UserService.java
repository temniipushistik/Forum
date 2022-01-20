package com.example.forumpilotproject.services;

import com.example.forumpilotproject.entities.EntityUser;
import com.example.forumpilotproject.entities.Role;
import com.example.forumpilotproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
    public boolean addUser(EntityUser user){
        EntityUser userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
           return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return true;
    }
}
