package com.restapi.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class UserDetailsRequest {
    private byte[] dp;
    private String companyName;
    private String designation;
    private String gitHub;
    private String youtube;
    private String linkedIn;
    private String instagram;
    private Long userId;
}
