package com.innopolis.eventgo.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "photo")
public class Photo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private byte[] filename;

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", post=" + post +
                ", filename=" + Arrays.toString(filename) +
                '}';
    }
}
