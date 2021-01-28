package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(name = Comment.findCommentByPostId, query = "select c from Comment c where c.post.id = :id_post")
public class Comment {

    public final static String findCommentByPostId = "Comment.findCommentByPostId";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment_text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_post", referencedColumnName = "id", nullable = false)
    private Post post;

    @Column(name = "VERSION")
    @Version
    private Integer version;

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", userId=" + user.getId() +
                ", postId=" + post.getId() +
                '}';
    }
}
