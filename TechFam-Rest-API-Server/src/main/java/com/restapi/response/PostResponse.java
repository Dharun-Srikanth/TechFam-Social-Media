package com.restapi.response;

import com.restapi.model.AppUser;
import com.restapi.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class PostResponse {
    private Long id;
    private String caption;
    private Long likeCount;
    private AppUser postUserId;
    private List<CommentResponse> comments;
    private String photo;
    private LocalDateTime createdAt;

}
