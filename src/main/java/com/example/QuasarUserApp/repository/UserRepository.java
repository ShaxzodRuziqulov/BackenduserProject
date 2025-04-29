/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.09.2024
 * TIME:17:39
 */
package com.example.QuasarUserApp.repository;

import com.example.QuasarUserApp.entity.User;
import com.example.QuasarUserApp.entity.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("update users u set u.status=:status where u.id=:id")
    public void updateByStatus(@Param("id") Long id, @Param("status") Status status);


    Optional<User> findByEmail(String email);

}
