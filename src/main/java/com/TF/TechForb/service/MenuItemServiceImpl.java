package com.TF.TechForb.service;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.MenuItem.MenuItem;
import com.TF.TechForb.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl {
    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getAll() {
        try {
            return menuItemRepository.findAll();
        } catch (Exception e) {
            throw new TechForbException("Error in get all", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public MenuItem createItem(MenuItem menuItem) {
        try {
            Optional<MenuItem> itemFounded = menuItemRepository.findByTitle(menuItem.getTitle());
            if(itemFounded.isPresent()) {
                throw new TechForbException("This menu item already exist", null, HttpStatus.BAD_REQUEST);
            } else {
                return menuItemRepository.save(menuItem);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in create item", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public MenuItem deleteItem(Long id) {
        try {
            Optional<MenuItem> menuItemFounded = menuItemRepository.findById(id);
            if(menuItemFounded.isPresent()) {
                menuItemRepository.delete(menuItemFounded.get());
                return menuItemFounded.get();
            } else {
                throw new TechForbException("Menu item not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in delete item", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
