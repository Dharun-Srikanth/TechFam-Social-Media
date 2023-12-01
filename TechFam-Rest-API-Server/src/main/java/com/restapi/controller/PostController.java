package com.restapi.controller;

import com.restapi.model.Post;
import com.restapi.repository.UserRepository;
import com.restapi.request.PostRequest;
import com.restapi.response.PostResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.PostService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@PreAuthorize("hasRole('ROLE_USER')")
public class PostController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StorageService storageService;

    @GetMapping
    public ResponseEntity<APIResponse> getAllPost(){
        List<Post> posts = postService.findAllPost();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(posts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserPost(@PathVariable Long id){
        List<PostResponse> posts = postService.findUserPost(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(posts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/following-post/{id}")
    public ResponseEntity<APIResponse> getUserFollowingPosts(@PathVariable Long id){
        List<PostResponse> followingPosts = postService.findFollowingPosts(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(followingPosts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/explore/{id}")
    public ResponseEntity<APIResponse> explorePosts(@PathVariable Long id){
        List<PostResponse> explore = postService.findExplorePosts(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(explore);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createPost(@RequestBody PostRequest postRequest){
        List<Post> postList = postService.createPost(postRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(postList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> createPost(
            @RequestParam("photo") MultipartFile image,
            @RequestParam("caption") String caption,
            @RequestParam("postUserId") Long postUserId
    ) throws IOException {

        System.out.println("photo: " + image.getOriginalFilename());
        System.out.println("caption: " + caption);

        String file = storageService.storeFile(image);

        Post post = new Post();
        post.setPostUserId(userRepository.findById(postUserId).get());
        post.setPhoto(file);
        post.setCaption(caption);

        List<Post> postList = postService.createNewPost(post);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(post);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<APIResponse> editPost(@RequestBody PostRequest postRequest){
        List<Post> postList = postService.editPost(postRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(postList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/likes/add/{id}")
    public void addLikes(@PathVariable Long id){
        postService.addLike(id);
    }

    @PostMapping("/likes/remove/{id}")
    public void removeLikes(@PathVariable Long id){
        postService.removeLike(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deletePost(@PathVariable Long id){
        List<Post> postList = postService.deletePost(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(postList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
