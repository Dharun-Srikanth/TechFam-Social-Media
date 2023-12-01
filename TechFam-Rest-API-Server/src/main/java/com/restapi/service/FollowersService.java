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
import com.restapi.response.FollowersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FollowersService {
    @Autowired
    private FollowersRepository followersRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthDto authDto;

    public List<Followers> findFollowersList() {
        return followersRepository.findAll();
    }

    public List<Following> findUserFollowersList(Long id) {
        List<Following> appUsers = followersRepository.findFollowersListByUserId(id);
//        return authDto.mapToFollowersResponse(appUsers);
        return appUsers;
    }

    @Transactional
    public List<Following> removeFollowers(Long id) {
        followersRepository.deleteById(id);
        return findUserFollowersList(id);
    }

    public List<Long> followersId(Long id) {
        return followersRepository.findFollowersId(id);
    }
}
