package com.restapi.dataloader;

import com.restapi.model.AppUser;
import com.restapi.model.Post;
import com.restapi.model.Role;
import com.restapi.repository.PostRepository;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

//        Create user roles
        Role userRole = createRoleIfNotFound(Role.USER);

//        Create user
        AppUser user = createUserIfNotFound("TechBoy", "user","user@gamil.com", userRole);

//        Sample Post
        createPostIfNotFound("My new Business", user, "1701165360303-file.png");
        createPostIfNotFound("Checking", user, null);

        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(final String username) {
        Role role = roleRepository.findByName(username);
        if (role == null) {
            role = new Role();
            role.setName(username);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    private Post createPostIfNotFound(final String caption, final AppUser postUserId, final String photo) {
        Optional<Post> post = postRepository.findById(1L);
        Post post1 = null;
        if (post.isEmpty()) {
            post1 = new Post();
            post1.setCaption(caption);
            post1.setPostUserId(postUserId);
            post1.setPhoto(photo);
            post1 = postRepository.save(post1);
        }
        return post1;
    }

    @Transactional
    private AppUser createUserIfNotFound(final String username, final String password, final String email,
                                         final Role role) {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = null;
        if (optionalUser.isEmpty()) {
            user = new AppUser();
            user.setUsername(username);
            user.setName(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setRoles(role);
            user.setEmail(email);
            user = userRepository.save(user);
        }
        return user;
    }
}
