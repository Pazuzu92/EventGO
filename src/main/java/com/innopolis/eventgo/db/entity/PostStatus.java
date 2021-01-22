package com.innopolis.eventgo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post_status")
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(name = PostStatus.getStatusById, query = "select s from PostStatus s where s.status = :id")
public class PostStatus {

    public final static String getStatusById = "PostStatus.getStatusById";

    public final static int MODERATED = 1;
    public final static int REJECTED = 2;
    public final static int ACTIVE = 3;
    public final static int ARCHIVED = 4;
    public final static int DELETED = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private int status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postStatus")
    private Set<Post> posts = new HashSet<>();

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
