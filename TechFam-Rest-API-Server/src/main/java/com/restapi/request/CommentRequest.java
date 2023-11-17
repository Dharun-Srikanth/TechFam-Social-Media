package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String comments;
    private Long userId;
    private Long postId;
}
