package com.restapi.controller;

import com.restapi.model.Post;
import com.restapi.request.PostRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@PreAuthorize("hasRole('ROLE_USER')")
public class PostController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<APIResponse> getAllPost(){
        List<Post> posts = postService.findAllPost();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(posts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserPost(@PathVariable Long id){
        List<Post> posts = postService.findUserPost(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(posts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/following-post/{id}")
    public ResponseEntity<APIResponse> getUserFollowingPosts(@PathVariable Long id){
        List<Post> followingPosts = postService.findFollowingPosts(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(followingPosts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createPost(@RequestBody PostRequest postRequest){
        List<Post> postList = postService.createPost(postRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(postList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<APIResponse> editPost(@RequestBody PostRequest postRequest){
        List<Post> postList = postService.editPost(postRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(postList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deletePost(@PathVariable Long id){
        List<Post> postList = postService.deletePost(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(postList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
