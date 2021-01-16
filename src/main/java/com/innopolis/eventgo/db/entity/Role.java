package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(name = "Role.findByCode", query = "select r from Role r where role_code = :code")
public class Role {

    private final int USER = 1;
    private final int MODERATOR = 2;
    private final int ADMIN = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_code")
    private int roleCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<User> users = new HashSet<>();

    @Column(name = "VERSION")
    @Version
    private int version;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleCode=" + roleCode +
                '}';
    }
}
