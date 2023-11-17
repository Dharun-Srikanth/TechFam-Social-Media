package com.restapi.service;

import com.restapi.dto.PostDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Following;
import com.restapi.model.Post;
import com.restapi.repository.FollowingRepository;
import com.restapi.repository.PostRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private PostDto postDto;

    public List<Post> findUserPost(Long id) {
         return postRepository.findUserPosts(id);
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public List<Post> createPost( PostRequest postRequest) {
        Post post = postDto.mapToPost(postRequest);
        AppUser user = userRepository.findById(postRequest.getPostUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("postUserId","postUserId",postRequest.getPostUserId()));
        post.setPostUserId(user);
        postRepository.save(post);
        return findAllPost();
    }

    public List<Post> editPost(PostRequest postRequest) {
        Post post = postDto.mapToPost(postRequest);
        AppUser user = userRepository.findById(postRequest.getPostUserId())
                .orElseThrow(() -> new ResourceNotFoundException("postUserId","postUserId",Long.parseLong(String.valueOf(postRequest.getPostUserId()))));
        post.setPostUserId(user);
        postRepository.save(post);
        return findAllPost();
    }

    public List<Post> deletePost(Long id) {
        postRepository.deleteById(id);
        return findAllPost();
    }

    public List<Post> findFollowingPosts(Long id) {
        List<Long> followingIds = followingRepository.findAllFollowingIds(id);
        return postRepository.findAllById(followingIds);
    }
}
