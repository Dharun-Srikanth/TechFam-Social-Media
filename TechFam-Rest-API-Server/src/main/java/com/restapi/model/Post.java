package com.restapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;

    @Column(name = "likes")
    private Long likeCount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser postUserId;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

}
