package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Getter
@Setter
public class PostRequest {

    private String caption;

    private Long likes=0L;

    private String photo;

    private Long postUserId;
}
