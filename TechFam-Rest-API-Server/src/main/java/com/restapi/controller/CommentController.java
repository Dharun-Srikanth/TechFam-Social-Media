package com.restapi.controller;

import com.restapi.model.Comment;
import com.restapi.request.CommentRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllComments(){
        List<Comment> comments = commentService.findAllComments();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(comments);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getCommentsByPostId(@PathVariable Long id){
        List<Comment> commentListByPost = commentService.findCommentsByPostId(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(commentListByPost);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createComment(@RequestBody CommentRequest commentRequest){
        List<Comment> commentList = commentService.createComment(commentRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(commentList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<APIResponse> editComment(@RequestBody CommentRequest commentRequest){
        List<Comment> commentList = commentService.editComment(commentRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(commentList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteComment(@PathVariable Long id){
        List<Comment> comments = commentService.deleteComment(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(comments);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
