package com.restapi.repository;

import com.restapi.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {

    @Query("FROM Following f WHERE f.following.id=:id")
    List<Following> findFollowingListByUserId(Long id);

    @Query("SELECT f.followingUser FROM Following f WHERE f.accepted=true AND f.id=:id")
    List<Long> findAllFollowingIds(Long id);

}
