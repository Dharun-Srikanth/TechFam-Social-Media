package com.restapi.repository;

import com.restapi.model.AppUser;
import com.restapi.model.Comment;
import com.restapi.model.Followers;
import com.restapi.model.Following;
import com.restapi.response.FollowersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Long> {

    @Query("FROM Following f WHERE f.followingUser.id=:id AND f.accepted=true")
    List<Following> findFollowersListByUserId(Long id);

    @Query("SELECT f.id FROM Followers f WHERE f.followers.id=:id")
    List<Long> findFollowersId(Long id);
}
