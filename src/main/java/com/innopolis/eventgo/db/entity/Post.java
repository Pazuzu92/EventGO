package com.innopolis.eventgo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_post_status", referencedColumnName = "id", nullable = false)
    private PostStatus postStatus;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "groups", joinColumns = {@JoinColumn(name = "id_post")}, inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_place", referencedColumnName = "id")
    private Place place;

    @Column(name = "VERSION")
    @Version
    private int version;
}
