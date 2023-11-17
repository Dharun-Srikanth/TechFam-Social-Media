package com.restapi.controller;

import com.restapi.model.Following;
import com.restapi.request.FollowingRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/following")
public class FollowingController {

    @Autowired
    private FollowingService followingService;

    @Autowired
    private APIResponse apiResponse;


    @GetMapping
    public ResponseEntity<APIResponse> getFollowingList(){
        List<Following> followingList = followingService.findFollowingList();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(followingList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserFollowingList(@PathVariable Long id){
        List<Following> userFollowingList = followingService.findUserFollowingList(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userFollowingList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addUserFollowingList(@RequestBody FollowingRequest followingRequest){
        List<Following> userFollowingList = followingService.addFollowing(followingRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userFollowingList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<APIResponse> removeFollowingList(@PathVariable Long id){
        List<Following> userFollowingList = followingService.removeFollowing(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userFollowingList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/accept/{id}")
    public ResponseEntity<APIResponse> acceptFollowRequest(@PathVariable Long id){
        List<Following> userFollowingList = followingService.acceptRequest(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userFollowingList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
