package com.restapi.repository;

import com.restapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("FROM Comment c WHERE c.post.id=:id")
    List<Comment> findCommentsById(Long id);
}
