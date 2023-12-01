package com.restapi.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class UserDetailsRequest {
    private String dp;
    private String companyName;
    private String designation;
    private String gitHubUrl;
    private String youtubeUrl;
    private String linkedInUrl;
    private String instagramUrl;
    private Long userId;
    private String aboutMe;
    private Long id;
}
