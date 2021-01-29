package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(name = Group.findFollowers, query = "select f from Group f where id_post = :id")
public class Group {

    public final static String findFollowers = "Group.findFollowers";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_post", referencedColumnName = "id", nullable = false)
    private Post post;

    @Column(name = "VERSION")
    @Version
    private Integer version;
}
