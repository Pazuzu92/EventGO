package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "likes")
    private int like;

    @Column(name = "dislikes")
    private int dislike;

    @Column(name = "date_create")
    private LocalDateTime date_create;

    @Column(name = "date_from")
    private LocalDateTime dateFrom;

    @Column(name = "date_to")
    private LocalDateTime dateTo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "post_photo", joinColumns = {@JoinColumn(name = "id_post")}, inverseJoinColumns = {@JoinColumn(name = "id_photo")})
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_post_status", referencedColumnName = "id", nullable = false)
    private PostStatus postStatus;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "groups", joinColumns = {@JoinColumn(name = "id_post")}, inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_place", referencedColumnName = "id")
    private Place place;

    @Column(name = "VERSION")
    @Version
    private int version;
}
