package com.example.forumpilotproject.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,ADMIN;


    @Override
    public String getAuthority() {
        //строковое представление значения роли
        return name();
    }
}

