package com.restapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "dp", columnDefinition = "TEXT")
    private String profile_picture;

    private String companyName;

    private String designation;

    private String gitHubUrl;

    private String youtubeUrl;

    private String linkedInUrl;

    private String instagramUrl;

    @Column(name = "about_me")
    private String aboutMe;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser detailsUser;
}
