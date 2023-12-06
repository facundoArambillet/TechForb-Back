package com.TF.TechForb.service;

import com.TF.TechForb.model.MenuItem.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> getAll();
    MenuItem createItem(MenuItem menuItem);
    MenuItem deleteItem(Long id);
}
