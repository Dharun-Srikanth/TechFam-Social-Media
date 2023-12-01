package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.model.UserDetails;
import com.restapi.repository.UserRepository;
import com.restapi.request.UserDetailsRequest;
import com.restapi.request.UserRequest;
import com.restapi.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailsDto {

    @Autowired
    private UserRepository userRepository;
    public UserDetails mapToUserDetails(UserDetailsRequest userDetailsRequest){
        UserDetails userDetails = new UserDetails();
        userDetails.setProfile_picture(userDetailsRequest.getDp());
        userDetails.setCompanyName(userDetailsRequest.getCompanyName());
        userDetails.setDesignation(userDetailsRequest.getDesignation());
        userDetails.setGitHubUrl(userDetailsRequest.getGitHubUrl());
        userDetails.setYoutubeUrl(userDetailsRequest.getYoutubeUrl());
        userDetails.setLinkedInUrl(userDetailsRequest.getLinkedInUrl());
        userDetails.setInstagramUrl(userDetailsRequest.getInstagramUrl());
        userDetails.setId(userDetailsRequest.getId());
        userDetails.setAboutMe(userDetailsRequest.getAboutMe());
        return userDetails;
    }

    public AppUser mapToUserResponse(UserRequest userRequest){
        AppUser userResponse = new AppUser();
        userResponse.setUsername(userRequest.getUsername());
        userResponse.setName(userRequest.getName());
        userResponse.setEmail(userRequest.getEmail());
        userResponse.setId(userRequest.getId());
        userResponse.setPassword(userRepository.findById(userRequest.getId()).get().getPassword());
        userResponse.setId(userRepository.findById(userRequest.getId()).get().getId());
        userResponse.setRoles(userRepository.findById(userRequest.getId()).get().getRoles());
        return userResponse;
    }



}
