package com.restapi.repository;

import com.restapi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    @Query("FROM AppUser u WHERE u.username=:username OR u.email=:email")
    Optional<AppUser> findByUsernameOrEmail(String username, String email);

    @Query("FROM AppUser u WHERE u.id=:id")
    AppUser findNewFriends(Long id);

    @Query("SELECT u.id FROM AppUser u")
    List<Long> findAllUserId();

}
