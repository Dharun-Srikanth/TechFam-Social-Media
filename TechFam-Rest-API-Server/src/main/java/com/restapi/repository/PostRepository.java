package com.restapi.repository;

import com.restapi.model.Following;
import com.restapi.model.Post;
import com.restapi.response.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("FROM Post p WHERE p.postUserId.id=:id")
    List<Post> findUserPosts(Long id);

    @Query("FROM Post p INNER JOIN Following f ON f.following.id=:id AND p.postUserId!=f.followingUser")
    List<Post> findExplore(Long id);

    @Query("SELECT p.likeCount FROM Post p WHERE id=:id")
    Long getLikeCount(Long id);

    @Modifying
    @Query("UPDATE Post p SET p.likeCount=:like WHERE id=:id")
    void addPostLike(Long like, Long id);
}
