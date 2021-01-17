package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "photo")
@NoArgsConstructor
@Getter
@Setter
public class Photo {

    public Photo(Post post, byte[] image) {
        this.post = post;
        this.image = image;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "image")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "id_post", referencedColumnName = "id")
    private Post post;

    @Version
    @Column(name = "version")
    private Long version;

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", image=" + image.hashCode() +
                '}';
    }
}
