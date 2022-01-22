package com.example.forumpilotproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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

    @ManyToOne(fetch = FetchType.EAGER )//указываем БД, что к куче собщеий бможнт бытьодин пользователь
    @JoinColumn(name ="user_id")//указываем БД как назвать поле автор
    private EntityUser author;

    public Message(String text, EntityUser user
    ) {
        this.text = text;
        this.author=user;

    }

public String getAuthorName(){
        return author!=null?author.getUsername():"<none>";
}

}

