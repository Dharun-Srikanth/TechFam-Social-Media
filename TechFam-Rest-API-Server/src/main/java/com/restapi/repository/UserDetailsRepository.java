package com.restapi.repository;

import com.restapi.model.Post;
import com.restapi.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query("FROM UserDetails u WHERE u.detailsUser.id=:id")
    Optional<UserDetails> getUserDetails(Long id);
}
