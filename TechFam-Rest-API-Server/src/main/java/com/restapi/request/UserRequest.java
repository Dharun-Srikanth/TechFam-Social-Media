package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private Long id;
    private String username;
    private String email;
    private String name;
}
