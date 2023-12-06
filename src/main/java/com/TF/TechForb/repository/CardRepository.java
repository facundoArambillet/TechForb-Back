package com.TF.TechForb.repository;

import com.TF.TechForb.model.Card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    Optional<Card> findByCardNumber(String numberCard);
    @Query("SELECT c FROM Card c WHERE c.account.idAccount = :idAccount")
    Optional<Card> findByAccount(@Param("idAccount") Long idAccount);

    @Query("SELECT c FROM Card c INNER JOIN Account a ON c.account.idAccount = a.idAccount " +
            "INNER JOIN User u ON a.user.idUser = u.idUser WHERE u.documentNumber = :documentNumber")
    Optional<Card> findByUserDocument(@Param("documentNumber") Integer documentNumber);
}
