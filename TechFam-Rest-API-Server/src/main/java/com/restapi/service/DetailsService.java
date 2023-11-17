package com.restapi.service;

import com.restapi.dto.DetailsDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Post;
import com.restapi.model.UserDetails;
import com.restapi.repository.UserDetailsRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.UserDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetailsDto detailsDto;

    public List<UserDetails> findAllDetails() {
        return userDetailsRepository.findAll();
    }

    public Optional<UserDetails> findDetails(Long id) {
        return userDetailsRepository.findById(id);
    }

    public Optional<UserDetails> createDetails(UserDetailsRequest userDetailsRequest) {
        UserDetails userDetails = detailsDto.mapToUserDetails(userDetailsRequest);
        AppUser user = userRepository.findById(userDetailsRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId","userId",userDetailsRequest.getUserId()));
        userDetails.setDetailsUser(user);
        userDetailsRepository.save(userDetails);
        return findDetails(user.getId());
    }

    public Optional<UserDetails> editDetails(UserDetailsRequest userDetailsRequest) {
        UserDetails userDetails = detailsDto.mapToUserDetails(userDetailsRequest);
        AppUser user = userRepository.findById(userDetailsRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId","userId",userDetailsRequest.getUserId()));
        userDetails.setDetailsUser(user);
        userDetailsRepository.save(userDetails);
        return findDetails(user.getId());
    }
}
