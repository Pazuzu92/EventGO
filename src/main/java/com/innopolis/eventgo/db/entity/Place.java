package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "place")
@NoArgsConstructor
@Getter
@Setter
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id", nullable = false)
    private City city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place")
    private Set<Post> posts = new HashSet<>();

    @Column(name = "VERSION")
    @Version
    private int version;
}
