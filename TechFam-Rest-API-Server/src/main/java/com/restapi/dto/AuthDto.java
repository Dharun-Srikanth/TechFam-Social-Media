
package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import com.restapi.response.FollowersResponse;
import com.restapi.response.FollowingResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthDto {

    public AppUser mapToAppUser(RegisterRequest user) {
        AppUser appUser = new AppUser();
        appUser.setUsername(user.getUsername());
        appUser.setName(user.getName());
        appUser.setPassword(user.getPassword());
        appUser.setEmail(user.getEmail());
        return appUser;
    }


    public AuthResponse mapToAuthResponse(AppUser appUser) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(appUser.getId());
        authResponse.setName(appUser.getName());
        authResponse.setUsername(appUser.getUsername());
        authResponse.setEmail(appUser.getEmail());
        return authResponse;
    }

    public List<FollowingResponse> mapToFollowingResponse(List<AppUser> appUser){
        List<FollowingResponse> followingResponseList=new ArrayList<>();

        for(AppUser user:appUser) {
            FollowingResponse followingResponse=new FollowingResponse();
        followingResponse.setId(user.getId());
            followingResponse.setUsername(user.getUsername());
            followingResponseList.add(followingResponse);
        }
        return followingResponseList;
    }

    public List<FollowersResponse> mapToFollowersResponse(List<AppUser> appUsers){
        List<FollowersResponse> followersResponseList = new ArrayList<>();

        for (AppUser user:appUsers){
            FollowersResponse followersResponse = new FollowersResponse();
            followersResponse.setId(user.getId());
            followersResponse.setUsername(user.getUsername());
            followersResponseList.add(followersResponse);
        }
        return followersResponseList;
    }
}
