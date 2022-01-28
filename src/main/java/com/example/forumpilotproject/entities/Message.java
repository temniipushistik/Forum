package com.example.forumpilotproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private String description;
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)//указываем БД, что к куче собщеий бможнт бытьодин пользователь
    @JoinColumn(name = "user_id")//делаем foreign key для юзера и указываем БД как назвать поле автор
    private EntityUser author;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<EntityComments> entityComments;

    public Message(String text, EntityUser user
    ) {
        this.text = text;
        this.author = user;
        entityComments = new ArrayList<>();

    }

    public Message(EntityUser user) {
        this.author = user;

    }

    public Message(String text, String description, EntityUser user
    ) {
        this.text = text;
        this.author = user;
        this.description = description;

    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

}

