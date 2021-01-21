package com.innopolis.eventgo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Role {

    public final static int USER = 1;
    public final static int MODERATOR = 2;
    public final static int ADMIN = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_code")
    private int roleCode;

    @JsonIgnore
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
