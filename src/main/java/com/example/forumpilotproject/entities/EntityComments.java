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
public class EntityComments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String textOfComment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private EntityUser authorOfComment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Message commentedPost;

    public EntityComments(String textOfComment, EntityUser user, Message message) {
        this.textOfComment = textOfComment;
        this.authorOfComment = user;
        this.commentedPost = message;

    }


}
