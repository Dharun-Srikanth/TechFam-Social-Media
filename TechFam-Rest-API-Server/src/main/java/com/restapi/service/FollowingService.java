package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Followers;
import com.restapi.model.Following;
import com.restapi.repository.FollowersRepository;
import com.restapi.repository.FollowingRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.FollowingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowingService {
    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private FollowingRequest followingRequest;

    @Autowired
    private FollowersRepository followersRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Following> findFollowingList() {
        return followingRepository.findAll();
    }

    public List<Following> findUserFollowingList(Long id) {
        return followingRepository.findFollowingListByUserId(id);
    }

    public List<Following> addFollowing(FollowingRequest followingRequest) {
        Following following = new Following();
        Followers followers = new Followers();
        AppUser user = userRepository.findById(followingRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", followingRequest.getUserId()));
        following.setFollowing(user);
        user = userRepository.findById(followingRequest.getFollowingUserId())
                .orElseThrow(() -> new ResourceNotFoundException("followingUserId", "followingUserId", followingRequest.getFollowingUserId()));
        following.setFollowingUser(user);
        followingRepository.save(following);

        user = userRepository.findById(followingRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", followingRequest.getUserId()));
        followers.setUserFollower(user);
        user = userRepository.findById(followingRequest.getFollowingUserId())
                .orElseThrow(() -> new ResourceNotFoundException("followingUserId", "followingUserId", followingRequest.getFollowingUserId()));
        followers.setFollowers(user);

        followersRepository.save(followers);

        return findFollowingList();
    }

    public List<Following> removeFollowing(Long id) {
        followingRepository.deleteById(id);
        return findFollowingList();
    }

    public List<Following> acceptRequest(Long id) {
        Following following = new Following();
        Followers followers = new Followers();

        following.setId(id);
        following.setFollowing(followingRepository.findById(id).get().getFollowing());
        following.setFollowingUser(followingRepository.findById(id).get().getFollowingUser());
        followingRepository.findById(id).get().setAccepted(true);
        following.setAccepted(followingRepository.findById(id).get().isAccepted());

        followingRepository.save(following);

        AppUser user = userRepository.findById(followingRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", followingRequest.getUserId()));
        followers.setUserFollower(user);
        user = userRepository.findById(followingRequest.getFollowingUserId())
                .orElseThrow(() -> new ResourceNotFoundException("followingUserId", "followingUserId", followingRequest.getFollowingUserId()));
        followers.setFollowers(user);

        followersRepository.save(followers);
        return followingRepository.findAll();
    }
}
