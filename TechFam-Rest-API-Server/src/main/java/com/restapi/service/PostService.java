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
import com.restapi.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public List<PostResponse> findUserPost(Long id) {
         List<Post> posts = postRepository.findUserPosts(id);
         return postDto.mapToPostResponse(posts);
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }


    @Transactional
    public List<Post> createPost( PostRequest postRequest) {
        Post post = postDto.mapToPost(postRequest);
        AppUser user = userRepository.findById(postRequest.getPostUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("postUserId","postUserId",postRequest.getPostUserId()));
        post.setPostUserId(user);
        postRepository.save(post);
        return findAllPost();
    }

    @Transactional
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

    public List<PostResponse> findFollowingPosts(Long id) {
        List<Long> followingIds = followingRepository.findAllFollowingIds(id);
        List<PostResponse> posts = new ArrayList<>();
        for (Long followingId:followingIds){
            List<Post> userPosts = postRepository.findUserPosts(followingId);
            List<PostResponse> postResponseList =  postDto.mapToPostResponse(userPosts);
            posts.addAll(postResponseList);
        }
        return posts;
    }

    public List<PostResponse> findExplorePosts(Long id) {
        List<Post> posts =  postRepository.findExplore(id);
        return postDto.mapToPostResponse(posts);
    }

    @Transactional
    public void addLike(Long id) {
        Long like = postRepository.getLikeCount(id);
        postRepository.addPostLike(like+1, id);
    }

    @Transactional
    public void removeLike(Long id) {
        Long like = postRepository.getLikeCount(id);
        postRepository.addPostLike(like-1, id);
    }

    public List<Post> createNewPost(Post post) {
        postRepository.save(post);
        return findAllPost();
    }
}
