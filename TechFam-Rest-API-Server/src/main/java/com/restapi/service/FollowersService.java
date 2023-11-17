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
public class FollowersService {
    @Autowired
    private FollowersRepository followersRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Followers> findFollowersList() {
        return followersRepository.findAll();
    }

    public List<Followers> findUserFollowersList(Long id) {
        return followersRepository.findFollowersListByUserId(id);
    }
}
