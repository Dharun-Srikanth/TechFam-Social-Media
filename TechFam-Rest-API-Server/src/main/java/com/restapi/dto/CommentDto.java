package com.restapi.dto;

import com.restapi.model.Comment;
import com.restapi.request.CommentRequest;
import org.springframework.stereotype.Component;

@Component
public class CommentDto {

    public Comment mapToComment(CommentRequest commentRequest){
        Comment comment = new Comment();
        comment.setComments(commentRequest.getComments());
        return comment;
    }

}
