package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@Setter
public class Role {

    public final static String USER = "USER";
    public final static String MODERATOR = "MODERATOR";
    public final static String ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "VERSION")
    @Version
    private Integer version;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleCode=" + roleCode +
                '}';
    }
}
