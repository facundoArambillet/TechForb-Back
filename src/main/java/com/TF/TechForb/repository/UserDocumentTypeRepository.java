package com.TF.TechForb.repository;

import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDocumentTypeRepository extends JpaRepository<UserDocumentType,Long> {

    Optional<UserDocumentType> findByType(String userDniType);
}
