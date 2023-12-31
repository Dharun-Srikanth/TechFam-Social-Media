package com.restapi.controller;

import com.restapi.model.Followers;
import com.restapi.model.Following;
import com.restapi.response.FollowersResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followers")
public class FollowersController {
    @Autowired
    private FollowersService followersService;

    @Autowired
    private APIResponse apiResponse;


    @GetMapping
    public ResponseEntity<APIResponse> getFollowingList(){
        List<Followers> followersList = followersService.findFollowersList();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(followersList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/followersId/{id}")
    public ResponseEntity<APIResponse> getFollowersId(@PathVariable Long id){
        List<Long> userFollowersList = followersService.followersId(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userFollowersList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserFollowingList(@PathVariable Long id){
        List<Following> userFollowersList = followersService.findUserFollowersList(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userFollowersList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<APIResponse> removeFollowers(@PathVariable Long id) {
        List<Following> userFollowersList = followersService.removeFollowers(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userFollowersList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
