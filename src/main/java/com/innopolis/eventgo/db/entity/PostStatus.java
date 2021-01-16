package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post_status")
@NoArgsConstructor
@Getter
@Setter
public class PostStatus {

    final static int MODERATED = 1;
    final static int REJECTED = 2;
    final static int ACTIVE = 3;
    final static int ARCHIVED = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private int status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post_status")
    private Set<PostStatus> postStatuses = new HashSet<>();

    @Version
    @Column(name = "version")
    private int version;

    @Override
    public String toString() {
        return "PostStatus{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
