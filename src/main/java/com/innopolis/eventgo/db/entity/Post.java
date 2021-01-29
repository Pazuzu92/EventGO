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

    @Column(name = "description", length = 10485760)
    private String description;

    @Column(name = "address")
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_likes", referencedColumnName = "id")
    private Likes likes;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_from")
    private LocalDateTime dateFrom;

    @Column(name = "date_to")
    private LocalDateTime dateTo;

    @Column(name = "photo")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_post_status", referencedColumnName = "id", nullable = false)
    private PostStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Group> groups = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id")
    private City city;

    @Column(name = "VERSION")
    @Version
    private Integer version;

}
