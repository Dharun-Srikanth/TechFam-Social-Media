package com.restapi.dto;

import com.restapi.model.UserDetails;
import com.restapi.request.UserDetailsRequest;
import org.springframework.stereotype.Component;

@Component
public class DetailsDto {
    public UserDetails mapToUserDetails(UserDetailsRequest userDetailsRequest){
        UserDetails userDetails = new UserDetails();
        userDetails.setProfile_picture(userDetailsRequest.getDp());
        userDetails.setCompanyName(userDetailsRequest.getCompanyName());
        userDetails.setDesignation(userDetailsRequest.getDesignation());
        userDetails.setGitHubUrl(userDetailsRequest.getGitHub());
        userDetails.setYoutubeUrl(userDetailsRequest.getYoutube());
        userDetails.setLinkedInUrl(userDetailsRequest.getLinkedIn());
        userDetails.setInstagramUrl(userDetailsRequest.getInstagram());
        return userDetails;
    }
}
