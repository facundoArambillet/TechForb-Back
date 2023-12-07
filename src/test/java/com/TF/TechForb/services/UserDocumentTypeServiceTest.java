package com.TF.TechForb.services;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import com.TF.TechForb.repository.UserDocumentTypeRepository;
import com.TF.TechForb.service.UserDocumentTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDocumentTypeServiceTest {

    @Mock
    private UserDocumentTypeRepository userDocumentTypeRepository;
    @InjectMocks
    private UserDocumentTypeServiceImpl userDocumentTypeService;

    @Test
    public void givenDocumentTypes_whenGetAll_thenListShouldNotBeNull() {
        List<UserDocumentType> types = new ArrayList<>();
        when(userDocumentTypeRepository.findAll()).thenReturn(types);

        assertEquals(userDocumentTypeService.getAll(), userDocumentTypeRepository.findAll());
    }
    @Test
    public void givenInvalidDocumentType_whenGetById_thenReturnNotFound() {
        TechForbException exceptionExpected = new TechForbException("Document Type not found", null, HttpStatus.NOT_FOUND);

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () -> userDocumentTypeService.getById(1L));

        assertEquals(exceptionExpected.getHttpStatus(),exceptionCaptured.getHttpStatus());
    }
    @Test
    public void givenAlreadyExistDocumentType_whenCreate_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("This Document type already exist ", null, HttpStatus.BAD_REQUEST);
        UserDocumentType userDocumentType = new UserDocumentType(1L,"DNI");

        when(userDocumentTypeRepository.findByType(userDocumentType.getType())).thenReturn(Optional.of(userDocumentType));

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                userDocumentTypeService.createDocumentType(userDocumentType));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());

    }
}
