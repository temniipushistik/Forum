package com.example.forumpilotproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usr")
public class EntityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
   // private String userEmail;
    //private String userPhoneNumber;
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)//сам создает таблицу с енамом,
    // fetch = FetchType.EAGER - подгружает сразу роль
    @CollectionTable(name ="user_role",joinColumns = @JoinColumn(name = "user_id")) //создаем таблицу для набора ролей,
    // которая будет будет соединяться с таблицей пользователей
    @Enumerated(EnumType.STRING)

    private Set<Role> roles;//коллекция сет состоит из объектом енам Role, который лежит в этом же пакете


}
