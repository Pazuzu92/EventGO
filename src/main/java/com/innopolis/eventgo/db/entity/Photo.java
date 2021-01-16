package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Getter
@Setter
@Table(name = "photo")
public class Photo {

    public Photo() {
    }

    public Photo(Post post, byte[] photo) {
        this.post = post;
        this.photo = photo;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_post", referencedColumnName = "id", nullable = false)
    private Post post;

    private byte[] photo;

    @Version
    @Column(name = "version")
    private Long version;

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", post=" + post.toString() +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}
