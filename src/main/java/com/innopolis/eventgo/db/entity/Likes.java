package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@NoArgsConstructor
@Getter
@Setter
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "count_likes")
    private Integer likes;

    @Column(name = "VERSION")
    @Version
    private Integer version;
}
