package com.restapi.dto;

import com.restapi.model.Post;
import com.restapi.request.PostRequest;
import com.restapi.response.CommentResponse;
import com.restapi.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostDto {

    @Autowired
    private CommentDto commentDto;

    public Post mapToPost(PostRequest postRequest) {
        Post post = new Post();
        post.setCaption(postRequest.getCaption());
        post.setPhoto(postRequest.getPhoto());
        post.setLikeCount(postRequest.getLikes());
        return post;
    }

    public List<PostResponse> mapToPostResponse(List<Post> userPosts) {
        List<PostResponse> postResponseList = new ArrayList<>();

        for (Post post : userPosts){
            PostResponse postResponse = new PostResponse();
            postResponse.setId(post.getId());
            postResponse.setCaption(post.getCaption());
            postResponse.setPhoto(post.getPhoto());
            postResponse.setLikeCount(post.getLikeCount());
            postResponse.setPostUserId(post.getPostUserId());
            postResponse.setComments(commentDto.mapToCommentResponse(post.getComments()));
            postResponse.setCreatedAt(post.getCreatedAt());
            postResponseList.add(postResponse);
        }
        return postResponseList;
    }
}
