package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dislikes")
@NoArgsConstructor
@Getter
@Setter
public class Dislikes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "count_dislikes")
    private Integer dislikes;

    @Column(name = "VERSION")
    @Version
    private Integer version;

}
