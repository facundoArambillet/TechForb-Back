package com.TF.TechForb.repository;

import com.TF.TechForb.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.documentNumber = :documentNumber")
    Optional<User> findByDocumentNumber(@Param("documentNumber") Integer documentNumber);
}
