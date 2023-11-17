package com.restapi.repository;

import com.restapi.model.Following;
import com.restapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("FROM Post p WHERE p.postUserId.id=:id")
    List<Post> findUserPosts(Long id);
}
