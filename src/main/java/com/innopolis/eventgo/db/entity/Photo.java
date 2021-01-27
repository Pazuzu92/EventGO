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
@NamedQuery(name = Photo.findPhotoWherePostIdEqualsPhotoId,
        query = "select p from Photo p inner join PostPhoto ph on p.id = ph.photo.id inner join Post po on ph.post.id = po.id where po.id = :id")
public class Photo {

    public final static String findPhotoWherePostIdEqualsPhotoId = "Photo.findPhotoWherePostIdEqualsPhotoId";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "image")
    private byte[] image;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "photo")
//    private List<PostPhoto> postPhotos = new ArrayList<>();

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
