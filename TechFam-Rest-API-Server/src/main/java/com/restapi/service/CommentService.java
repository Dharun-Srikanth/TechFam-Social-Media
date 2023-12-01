package com.restapi.service;

import com.restapi.dto.CommentDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Comment;
import com.restapi.model.Post;
import com.restapi.repository.CommentRepository;
import com.restapi.repository.PostRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CommentRequest;
import com.restapi.response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentDto commentDto;


    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public List<CommentResponse> findCommentsByPostId(Long id) {
        List<Comment> comments = commentRepository.findCommentsById(id);
        return commentDto.mapToCommentResponse(comments);
    }

    @Transactional
    public List<CommentResponse> createComment(CommentRequest commentRequest) {
        Comment comment = commentDto.mapToComment(commentRequest);
        AppUser user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", commentRequest.getUserId()));
        comment.setUserComments(user);
        Post post = postRepository.findById(commentRequest.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("postId", "postId", commentRequest.getPostId()));
        comment.setPost(post);
        commentRepository.save(comment);
        return findCommentsByPostId(commentRequest.getPostId());
    }

    @Transactional
    public List<Comment> editComment(CommentRequest commentRequest) {
        Comment comment = commentDto.mapToComment(commentRequest);
        AppUser user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", commentRequest.getUserId()));
        comment.setUserComments(user);
        Post post = postRepository.findById(commentRequest.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("postId", "postId", commentRequest.getPostId()));
        comment.setPost(post);
        commentRepository.save(comment);
        return findAllComments();
    }

    public List<Comment> deleteComment(Long id) {
        commentRepository.deleteById(id);
        return findAllComments();
    }
}
