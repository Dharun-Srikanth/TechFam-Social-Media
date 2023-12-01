package com.restapi.service;

import com.restapi.dto.AuthDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Followers;
import com.restapi.model.Following;
import com.restapi.repository.FollowersRepository;
import com.restapi.repository.FollowingRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.FollowingRequest;
import com.restapi.response.FollowingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Autowired
   private AuthDto authDto;

    public List<Following> findFollowingList() {
        return followingRepository.findAll();
    }

    public List<Following> findUserFollowingList(Long id) {
        List<Following> appUsers= followingRepository.findFollowingListByUserId(id);
        return appUsers;

    }

    @Transactional
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


    @Transactional
    public List<Following> removeFollowing(Long id) {
        followingRepository.deleteById(id);
        return findFollowingList();
    }

    @Transactional
    public List<Following> removeFriend(Long id, Long fId) {
        followingRepository.removeFriend(id, fId);
        return findFollowingList();
    }


    @Transactional
    public void acceptRequest(Long id) {
        followingRepository.acceptRequest(id);
    }

    public List<Following> findRequest(Long id) {
        return followingRepository.findRequests(id);
    }


    public List<AppUser> findSuggestions(Long id) {
        List<Long> followingIds = followingRepository.findFriendSuggestions(id);
        List<Long> allUserIds = userRepository.findAllUserId();
        List<Long> suggestionsId = new ArrayList<>();
        boolean isPresent = false;
        for (Long userId: allUserIds) {
            isPresent = false;
            for (Long followingId: followingIds) {
                if(Objects.equals(followingId, userId)){
                    isPresent = false;
                    break;
                }else {
                    isPresent = true;
                }
            }
            suggestionsId.add(userId);
        }

        List<AppUser> appUsers = new ArrayList<>();

        for (Long userId : suggestionsId){
            if(!Objects.equals(userId, id))
                appUsers.add(userRepository.findNewFriends(userId));
        }

        return appUsers;

    }
}
