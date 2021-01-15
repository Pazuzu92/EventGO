package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Version
    @Column(name = "version")
    private Long version;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", user=" + user +
                ", version=" + version +
                '}';
    }
}
