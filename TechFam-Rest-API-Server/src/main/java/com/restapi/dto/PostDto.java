package com.restapi.dto;

import com.restapi.model.Post;
import com.restapi.request.PostRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class PostDto {

    public Post mapToPost(PostRequest postRequest) {
        Post post = new Post();
        post.setCaption(postRequest.getCaption());
        post.setPhoto(postRequest.getPhoto());
        post.setLikeCount(postRequest.getLikes());
        return post;
    }
}
