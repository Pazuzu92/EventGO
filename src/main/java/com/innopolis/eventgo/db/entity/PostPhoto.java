package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "post_photo")
@NoArgsConstructor
@Getter
@Setter
public class PostPhoto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_photo", referencedColumnName = "id", nullable = false)
    private Photo photo;

    @ManyToOne
    @JoinColumn(name = "id_post", referencedColumnName = "id", nullable = false)
    private Post post;

    @Column(name = "VERSION")
    @Version
    private Integer version;
}
