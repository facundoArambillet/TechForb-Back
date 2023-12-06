package com.TF.TechForb.controller;

import com.TF.TechForb.model.MenuItem.MenuItem;
import com.TF.TechForb.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-item")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public List<MenuItem> getAll() {
        return menuItemService.getAll();
    }
    @PostMapping
    public MenuItem createItem(@RequestBody MenuItem menuItem) {
        return menuItemService.createItem(menuItem);
    }
    @DeleteMapping("/{idItem}")
    public MenuItem deleteItem(@PathVariable("idItem") Long idItem) {
        return menuItemService.deleteItem(idItem);
    }
}
