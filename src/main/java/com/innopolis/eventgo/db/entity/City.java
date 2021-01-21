package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<Place> places = new HashSet<>();

    @Column(name = "VERSION")
    @Version
    private int version;
}
