package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comments")
@NamedQuery(name = "Comment.findByUserId", query = "select c from Comment c where user.id = :idUser")
//@NamedQuery(name = "Comments.findByPost", query = "select c from Comments c where post.id = :idPost")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment_text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "id_post", referencedColumnName = "id", nullable = false)
//    private Post post;

    @Column(name = "VERSION")
    @Version
    private int version;

    public Comment() {
    }
    public Comment(User user/*, Post post*/) {
        this.user = user;
//        this.post = post;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user.toString() +
//                ", post=" + post.toString() +
                '}';
    }
}
