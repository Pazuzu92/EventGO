package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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

    @Column(name = "city_name", unique = true)
    private String cityName;

    @Column(name = "short_name", unique = true)
    private String shortName;

    @Column(name = "VERSION")
    @Version
    private Integer version;
}
