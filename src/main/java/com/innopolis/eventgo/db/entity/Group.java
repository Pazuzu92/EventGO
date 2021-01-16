package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "group")
public class Group {

    public Group() {
    }

    public Group(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Post post;

    @Version
    @Column(name = "version")
    private Long version;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", post=" + post.toString() +
                '}';
    }
}
