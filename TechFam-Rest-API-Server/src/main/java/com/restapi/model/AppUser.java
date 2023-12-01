package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users") // don't use User
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    @NotEmpty
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    @NotEmpty
//    @Email(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,4}", message = "Invalid Email")
    private String email;

    @NotEmpty
    @Size(min = 2, message = "Password should have at least 2 characters")
    private String password;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    @JsonIgnore
    @OneToOne(mappedBy = "detailsUser")
    private UserDetails userDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "following")
    private List<Following> followingList;

    @JsonIgnore
    @OneToMany(mappedBy = "followingUser")
    private List<Following> userfollowingList;

    @JsonIgnore
    @OneToMany(mappedBy = "followers")
    private List<Followers> followersList;

    @JsonIgnore
    @OneToMany(mappedBy = "userFollower")
    private List<Followers> userFollowerList;

    @JsonIgnore
    @OneToMany(mappedBy = "postUserId")
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "userComments")
    private List<Comment> comments;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
