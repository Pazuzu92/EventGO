package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "photo")
@NoArgsConstructor
@Getter
@Setter
public class Photo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "image")
    private byte[] image;

    @ManyToMany(mappedBy = "photos")
    private List<Post> posts = new ArrayList<>();

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
