package com.restapi.dto;

import com.restapi.model.Comment;
import com.restapi.request.CommentRequest;
import com.restapi.response.CommentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentDto {

    public Comment mapToComment(CommentRequest commentRequest){
        Comment comment = new Comment();
        comment.setComments(commentRequest.getComments());
        return comment;
    }

    public List<CommentResponse> mapToCommentResponse(List<Comment> commentRequests){
        List<CommentResponse> comments = new ArrayList<>();
        for (Comment commentRequest:commentRequests){
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setComments(commentRequest.getComments());
            commentResponse.setId(commentRequest.getId());
            commentResponse.setUser(commentRequest.getUserComments());

            comments.add(commentResponse);
        }
        return comments;
    }

}
