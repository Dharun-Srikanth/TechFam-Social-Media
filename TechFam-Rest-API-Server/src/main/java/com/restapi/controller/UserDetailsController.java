package com.restapi.controller;

import com.restapi.model.AppUser;
import com.restapi.model.Post;
import com.restapi.model.UserDetails;
import com.restapi.repository.UserRepository;
import com.restapi.request.UserDetailsRequest;
import com.restapi.request.UserRequest;
import com.restapi.response.UserResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DetailsService;
import com.restapi.service.StorageService;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/details")
public class UserDetailsController {

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/user/{id}")
    public ResponseEntity<APIResponse> viewUser(@PathVariable Long id){
        Optional<AppUser> userDetails = detailsService.findUser(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userDetails);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/user/edit")
    public ResponseEntity<APIResponse> editUserDetails(@RequestBody UserRequest appUser){
        AppUser userDetails = detailsService.editUser(appUser);
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> createPost(
            @RequestParam("profile_picture") MultipartFile profile_picture,
            @RequestParam("companyName") String companyName,
            @RequestParam("id") Long id,
            @RequestParam("designation") String designation,
            @RequestParam("gitHubUrl") String gitHubUrl,
            @RequestParam("youtubeUrl") String youtubeUrl,
            @RequestParam("linkedInUrl") String linkedInUrl,
            @RequestParam("instagramUrl") String instagramUrl,
            @RequestParam("aboutMe") String aboutMe,
            @RequestParam("detailsUser") Long detailsUser
    ) throws IOException {

        String file = storageService.storeFile(profile_picture);

        UserDetails userDetails = new UserDetails();
        userDetails.setId(id);
        userDetails.setDetailsUser(userRepository.findById(detailsUser).get());
        userDetails.setAboutMe(aboutMe);
        userDetails.setInstagramUrl(instagramUrl);
        userDetails.setYoutubeUrl(youtubeUrl);
        userDetails.setLinkedInUrl(linkedInUrl);
        userDetails.setGitHubUrl(gitHubUrl);
        userDetails.setDesignation(designation);
        userDetails.setCompanyName(companyName);
        userDetails.setProfile_picture(file);

        Optional<UserDetails> userDetailsList = detailsService.createNewDetails(userDetails);

        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userDetailsList);

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
