package com.restapi.controller;

import com.restapi.model.AppUser;
import com.restapi.model.Following;
import com.restapi.request.FollowingRequest;
import com.restapi.response.FollowingResponse;
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

    @GetMapping("/request/{id}")
    public ResponseEntity<APIResponse> getRequests(@PathVariable Long id){
        List<Following> requestList = followingService.findRequest(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(requestList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/suggestion/{id}")
    public ResponseEntity<APIResponse> getSuggestions(@PathVariable Long id){
        List<AppUser> requestList = followingService.findSuggestions(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(requestList);
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

    @DeleteMapping("/remove/{id}/{fId}")
    public ResponseEntity<APIResponse> removeFriendList(@PathVariable Long id, @PathVariable Long fId){
        List<Following> userFollowingList = followingService.removeFriend(id, fId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userFollowingList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/accept/{id}")
    public void acceptFollowRequest(@PathVariable Long id){
        followingService.acceptRequest(id);
    }
}
