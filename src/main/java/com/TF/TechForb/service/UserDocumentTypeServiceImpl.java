package com.TF.TechForb.service;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import com.TF.TechForb.repository.UserDocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDocumentTypeServiceImpl implements UserDocumentTypeService{
    @Autowired
    private UserDocumentTypeRepository userDocumentTypeRepository;

    public List<UserDocumentType> getAll() {
        try {
            return userDocumentTypeRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public UserDocumentType getById(Long id) {
        try {
            Optional<UserDocumentType> documentTypeFounded = userDocumentTypeRepository.findById(id);
            if(documentTypeFounded.isPresent()) {
                return documentTypeFounded.get();
            } else {
                throw new TechForbException("Document Type not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in get by id", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserDocumentType getByType(String type) {
        try {
            Optional<UserDocumentType> userDocumentTypeFounded = userDocumentTypeRepository.findByType(type);
            if(userDocumentTypeFounded.isPresent()) {
                return userDocumentTypeFounded.get();
            } else {
                throw new TechForbException("Document type not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in get by type", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserDocumentType createDocumentType(UserDocumentType userDocumentType) {
        try {
            Optional<UserDocumentType> userDocumentTypeFounded = userDocumentTypeRepository.findByType(userDocumentType.getType());
            if(userDocumentTypeFounded.isPresent()) {
                throw new TechForbException("This Document type already exist ", null, HttpStatus.BAD_REQUEST);
            } else {
                userDocumentType.setType(userDocumentType.getType().toUpperCase());
                return userDocumentTypeRepository.save(userDocumentType);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Create Document type", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserDocumentType deleteDocumentType(Long id) {
        try {
            Optional<UserDocumentType> documentTypeFounded = userDocumentTypeRepository.findById(id);
            if(documentTypeFounded.isPresent()) {
                userDocumentTypeRepository.delete(documentTypeFounded.get());
                return documentTypeFounded.get();
            } else {
                throw new TechForbException("Document Type not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Delete Document type", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
