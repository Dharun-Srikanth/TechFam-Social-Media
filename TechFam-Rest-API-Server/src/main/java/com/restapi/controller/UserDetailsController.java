package com.restapi.controller;

import com.restapi.model.Post;
import com.restapi.model.UserDetails;
import com.restapi.request.UserDetailsRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/details")
public class UserDetailsController {

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private APIResponse apiResponse;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllUserDetails(){
        List<UserDetails> userDetails = detailsService.findAllDetails();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userDetails);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserDetails(@PathVariable Long id){
        Optional<UserDetails> userDetails = detailsService.findDetails(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userDetails);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createUserDetails(@RequestBody UserDetailsRequest userDetailsRequest){
        Optional<UserDetails> userDetails = detailsService.createDetails(userDetailsRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userDetails);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<APIResponse> editUserDetails(@RequestBody UserDetailsRequest userDetailsRequest){
        Optional<UserDetails> userDetails = detailsService.editDetails(userDetailsRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userDetails);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
