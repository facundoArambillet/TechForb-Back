package com.TF.TechForb.services;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.MenuItem.MenuItem;
import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import com.TF.TechForb.repository.MenuItemRepository;
import com.TF.TechForb.service.MenuItemServiceImpl;
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
public class MenuItemServiceTest {
    @Mock
    private MenuItemRepository menuItemRepository;
    @InjectMocks
    private MenuItemServiceImpl menuItemService;

    @Test
    public void givenMenuItem_whenGetAll_thenListShouldNotBeNull() {
        List<MenuItem> menuItems = new ArrayList<>();
        when(menuItemRepository.findAll()).thenReturn(menuItems);

        assertEquals(menuItemService.getAll(), menuItemRepository.findAll());
    }

    @Test
    public void givenAlreadyExistMenuItem_whenCreate_thenReturnThrowable() {
        TechForbException exceptionExpected = new TechForbException("This menu item already exist", null, HttpStatus.BAD_REQUEST);
        MenuItem menuItem = new MenuItem(1L,"Icon","Title");

        when(menuItemRepository.findByTitle(menuItem.getTitle())).thenReturn(Optional.of(menuItem));

        TechForbException exceptionCaptured = assertThrows(TechForbException.class, () ->
                menuItemService.createItem(menuItem));

        assertEquals(exceptionExpected.getMessage(), exceptionCaptured.getMessage());
        assertEquals(exceptionExpected.getHttpStatus(), exceptionCaptured.getHttpStatus());

    }
}
