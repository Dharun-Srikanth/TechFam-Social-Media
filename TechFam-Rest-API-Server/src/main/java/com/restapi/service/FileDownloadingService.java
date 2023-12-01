package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Post;
import com.restapi.model.UserDetails;
import com.restapi.repository.PostRepository;
import com.restapi.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import javax.xml.stream.Location;
import java.io.File;
import java.io.IOException;

@Service
public class FileDownloadingService {
    @Autowired
    StorageService storageService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public File getFile(long id) throws IOException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

        Resource resource = storageService.loadFileAsResource(post.getPhoto());

        System.out.println(resource.getFile().getName());

        return resource.getFile();
    }

    public File getUserDetailFile(long id) throws IOException {
        UserDetails userDetails = userDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

        Resource resource = storageService.loadFileAsResource(userDetails.getProfile_picture());

        System.out.println(resource.getFile().getName());

        return resource.getFile();
    }

}
