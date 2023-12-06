package com.TF.TechForb.repository;

import com.TF.TechForb.model.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("SELECT a FROM Account a WHERE a.user.idUser = :idUser")
    Optional<Account> findByUser(@Param("idUser") Long idUser);
}
