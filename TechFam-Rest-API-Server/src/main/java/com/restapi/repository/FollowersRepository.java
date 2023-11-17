package com.restapi.repository;

import com.restapi.model.Comment;
import com.restapi.model.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {

    @Query("FROM Followers f WHERE f.followers.id=:id")
    List<Followers> findFollowersListByUserId(Long id);
}
