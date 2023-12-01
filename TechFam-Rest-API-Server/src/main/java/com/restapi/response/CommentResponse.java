package com.restapi.response;

import com.restapi.model.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponse {

    private Long id;
    private String comments;
    private AppUser user;
}
