package com.restapi.repository;

import com.restapi.model.AppUser;
import com.restapi.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {

    @Query("FROM Following f WHERE f.following.id=:id AND f.accepted=true")
    List<Following> findFollowingListByUserId(Long id);

    @Query("SELECT f.followingUser.id FROM Following f WHERE f.following.id=:id AND f.accepted=true")
    List<Long> findFriendSuggestions(Long id);

    @Query("SELECT f.followingUser.id FROM Following f WHERE f.accepted=true AND f.following.id=:id")
    List<Long> findAllFollowingIds(Long id);

    @Query("FROM Following f WHERE f.accepted=false AND f.followingUser.id=:id")
    List<Following> findRequests(Long id);

    @Modifying
    @Query("UPDATE Following f SET f.accepted = 1 WHERE f.id=:id")
    void acceptRequest(Long id);

    @Modifying
    @Query("DELETE FROM Following f WHERE f.following.id=:userId AND f.followingUser.id=:followingId")
    void removeFriend(Long userId, Long followingId);

}
